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
import dev.icerock.moko.network.generated.apis.AuthenticationApi
import dev.icerock.moko.network.generated.apis.QueueApi
import dev.icerock.moko.network.generated.apis.ShiftsApi
import dev.icerock.moko.network.generated.apis.StudentApi
import dev.icerock.moko.network.generated.models.TokenRefreshRequest
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

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }

    singleOf(::createHttpClient)

    // Student
    factory<StudentApi> {
        StudentApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }

    // Authentication
    factory<AuthenticationApi> {
        AuthenticationApi(
            basePath = baseUrl,
            httpClient = createHttpClient(
                json = get(),
                authenticationApi = null
            ),
            json = get()
        )
    }

    // Shifts
    factory <ShiftsApi> {
        ShiftsApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }

    // Tables
    factory <ShiftsApi> {
        ShiftsApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }

    // Queue
    factory <QueueApi> {
        QueueApi(
            basePath = baseUrl,
            httpClient = get(),
            json = get()
        )
    }

}

private fun createHttpClient(
    json: Json,
    authenticationApi: AuthenticationApi?,
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

        if (authenticationApi != null) {
            install(RefreshTokenPlugin) {
                isCredentialsActual = { request ->
                    request.headers["Authorization"] == keyValueStorage.accessToken?.let { "Bearer $it" }
                }
                updateTokenHandler = {
                    try {
                        val response = authenticationApi.apiV1EmployeeTokensRefreshPostResponse(
                            tokenRefreshRequest = TokenRefreshRequest(
                                accessToken = keyValueStorage.accessToken.let { "" },
                                refreshToken = keyValueStorage.refreshToken.let { "" },
                            )
                        )

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