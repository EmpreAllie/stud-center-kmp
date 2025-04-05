package com.studcenter.di

import com.studcenter.data.infrastructure.KeyValueStorage
import com.studcenter.data.infrastructure.NativeHost
import com.studcenter.data.model.CustomResponseException
import com.studcenter.data.utils.CustomExceptionParser
import com.studcenter.data.utils.Log
import com.studcenter.entity.DeadTokenException
import dev.icerock.moko.network.createHttpClientEngine
import dev.icerock.moko.network.exceptionfactory.HttpExceptionFactory
import dev.icerock.moko.network.exceptionfactory.parser.ValidationExceptionParser
import dev.icerock.moko.network.generated.apis.AuthorizationApi
import dev.icerock.moko.network.generated.apis.CustomerApi
import dev.icerock.moko.network.generated.apis.InfoApi
import dev.icerock.moko.network.generated.apis.QueueApi
import dev.icerock.moko.network.generated.models.TokenRequest
import dev.icerock.moko.network.plugins.ExceptionPlugin
import dev.icerock.moko.network.plugins.RefreshTokenPlugin
import dev.icerock.moko.network.plugins.TokenPlugin
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.mp.KoinPlatform.getKoin


@Suppress("LongMethod")
val networkModule: Module = module {
    val baseUrl: String = NativeHost.getUrl()

    single<Json> { Json { ignoreUnknownKeys = true } }

    singleOf(::createHttpClient)

    factory<AuthorizationApi> {
        AuthorizationApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }

    // Authentication
    factory<CustomerApi> {
        CustomerApi(
            basePath = baseUrl,
            httpClient = createHttpClient(
                json = get(),
                authorizationApi = null
            ),
            json = get()
        )
    }
    // Shifts
    factory<InfoApi> {
        InfoApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }
    // Shifts
    factory<QueueApi> {
        QueueApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }
}

private fun createHttpClient(
    json: Json,
    authorizationApi: AuthorizationApi?,
): HttpClient {
    val keyValueStorage: KeyValueStorage = getKoin().get()
    Log("ACCESS TOKEN", keyValueStorage.accessToken.toString())
    Log("REFRESH TOKEN", keyValueStorage.refreshToken.toString())

    return HttpClient(createHttpClientEngine()) {
        install(ExceptionPlugin) {
            exceptionFactory = HttpExceptionFactory(
                defaultParser = CustomExceptionParser(json),
                customParsers = mapOf(
                    HttpStatusCode.UnprocessableEntity.value to ValidationExceptionParser(json)
                )
            )
        }

        expectSuccess = false

        authorizationApi?.let { api ->
            install(RefreshTokenPlugin) {
                isCredentialsActual = { request ->
                    request.headers["Authorization"] == keyValueStorage.accessToken?.let { "Bearer $it" }
                }
                updateTokenHandler = {
                    try {
                        val tokenRequest = TokenRequest(
                            accessToken = keyValueStorage.accessToken?.ifEmpty { "" },
                            refreshToken = keyValueStorage.refreshToken?.ifEmpty { "" },
                        )

                        val response = api.refreshTokenResponse(tokenRequest)

                        val body = response.body()
                        keyValueStorage.accessToken = body.accessToken
                        keyValueStorage.refreshToken = body.refreshToken

                        response.httpResponse.status == HttpStatusCode.OK
                    } catch (e: CustomResponseException) {
                        e.printStackTrace()

                        keyValueStorage.accessToken = null
                        keyValueStorage.refreshToken = null

                        throw DeadTokenException(
                            message = e.message ?: "",
                            cause = e
                        )
                    } catch (exc: Exception) {
                        exc.printStackTrace()

                        keyValueStorage.accessToken = null
                        keyValueStorage.refreshToken = null

                        throw DeadTokenException(
                            message = exc.message ?: "",
                            cause = exc
                        )
                    }
                }
            }
            install(TokenPlugin) {
                tokenHeaderName = "Authorization"
                tokenProvider = TokenPlugin.TokenProvider {
                    keyValueStorage.accessToken?.let { "Bearer $it" }
                }
            }
        }
    }
}