package com.studcenter.features.authorization.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.data.utils.localize
import com.studcenter.features.authorization.domain.AuthorizationRepository
import com.studcenter.features.authorization.domain.exception.LoginException
import com.studcenter.features.authorization.domain.exception.PasswordException
import com.studcenter.resources.MultiplatformResource
import dev.icerock.moko.network.generated.apis.AuthenticationApi
import dev.icerock.moko.network.generated.models.EmployeeLoginRequest
import kotlin.math.log

class AuthorizationRepositoryImpl(
    private val configParams: ConfigParams,
    private val authenticationApi: AuthenticationApi
): AuthorizationRepository {
    override suspend fun login(login: String, password: String): Boolean {
        if (isCorrectLogin(value = login)) {
            throw LoginException(message = MultiplatformResource.strings.loginError.localize())
        }

        if (isCorrectPassword(value = password)) {
            throw PasswordException(message = MultiplatformResource.strings.passwordError.localize())
        }

        val keyValueStorage = configParams.keyValueStorage
        val employeeLoginRequest = EmployeeLoginRequest(login = login, password = password)
        val result = authenticationApi.apiV1EmployeeLoginPostResponse(employeeLoginRequest = employeeLoginRequest)

        val body = result.body()

        keyValueStorage.accessToken = body.accessToken
        keyValueStorage.refreshToken = body.refreshToken

        return true
    }

    private fun isCorrectLogin(value: String): Boolean {
        val regex = "^[a-zA-Z0-9]{4,16}$".toRegex()
        return regex.matches(value)
    }

    private fun isCorrectPassword(value: String): Boolean {
        val regex = "^[a-zA-Z0-9]{4,16}$".toRegex()
        return regex.matches(value)
    }
}