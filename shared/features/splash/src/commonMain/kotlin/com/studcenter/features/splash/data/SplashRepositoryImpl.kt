package com.studcenter.features.splash.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.data.model.CustomResponseException
import com.studcenter.entity.DeadTokenException
import com.studcenter.features.splash.domain.SplashRepository
import dev.icerock.moko.network.generated.apis.AuthenticationApi
import dev.icerock.moko.network.generated.models.TokenRefreshRequest

class SplashRepositoryImpl(private val configParams: ConfigParams, private val authenticationApi: AuthenticationApi): SplashRepository {
    override suspend fun isActualVersionApp(): Boolean {
        val mobileVersion = configParams.configAppProvider.versionApp

        // TODO: Запрос на проверку версии
        val currentVersion = "1.2"

        return mobileVersion == currentVersion
    }

    override suspend fun isAuthorized(): Boolean {
        val keyValueStorage = configParams.keyValueStorage

        try {
            val accessToken = keyValueStorage.accessToken
            val refreshToken = keyValueStorage.refreshToken

            if (accessToken.isNullOrBlank() || refreshToken.isNullOrEmpty()) {
                return false
            }

            val tokenRefreshRequest = TokenRefreshRequest(
                accessToken = accessToken,
                refreshToken = refreshToken
            )

            val result = true
              // TODO: /*  authenticationApi.apiV1EmployeeTokensRefreshPost(tokenRefreshRequest = tokenRefreshRequest)*/

            // keyValueStorage.accessToken = result.accessToken
          // keyValueStorage.refreshToken = result.refreshToken

            return result
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