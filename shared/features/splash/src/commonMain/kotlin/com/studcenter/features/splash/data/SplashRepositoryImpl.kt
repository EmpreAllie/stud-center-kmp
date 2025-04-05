package com.studcenter.features.splash.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.data.model.CustomResponseException
import com.studcenter.entity.DeadTokenException
import com.studcenter.features.splash.domain.SplashRepository
import dev.icerock.moko.network.generated.apis.AuthorizationApi
import dev.icerock.moko.network.generated.apis.InfoApi
import dev.icerock.moko.network.generated.models.TokenRequest

class SplashRepositoryImpl(
    private val configParams: ConfigParams,
    private val authorizationApi: AuthorizationApi,
    private val infoApi: InfoApi,
) : SplashRepository {
    override suspend fun isActualVersionApp(): Boolean {
        val mobileVersion = configParams.configAppProvider.versionApp
        val currentVersion = mobileVersion.split(".")
        val version = infoApi.getVersion()

        val major = currentVersion.getOrNull(0)?.toIntOrNull()
        return major == version.major
    }

    override suspend fun isAuthorized(): Boolean {
        val keyValueStorage = configParams.keyValueStorage

        try {
            val accessToken = keyValueStorage.accessToken
            val refreshToken = keyValueStorage.refreshToken

            if (accessToken.isNullOrBlank() || refreshToken.isNullOrEmpty()) {
                return false
            }

            val tokenRequest = TokenRequest(accessToken = accessToken, refreshToken = refreshToken)
            val result = authorizationApi.refreshToken(tokenRequest)

            keyValueStorage.accessToken = result.accessToken
            keyValueStorage.refreshToken = result.refreshToken

            return true
        } catch (e: CustomResponseException) {
            e.printStackTrace()

            keyValueStorage.accessToken = null
            keyValueStorage.refreshToken = null
            return false

        } catch (e: DeadTokenException) {
            e.printStackTrace()
            keyValueStorage.accessToken = null
            keyValueStorage.refreshToken = null

            return false
        } catch (e: Exception) {
            e.printStackTrace()

            throw e
        }
    }
}