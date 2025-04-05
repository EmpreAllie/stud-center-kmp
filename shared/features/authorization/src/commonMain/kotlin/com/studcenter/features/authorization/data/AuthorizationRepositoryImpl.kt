package com.studcenter.features.authorization.data

import com.studcenter.data.utils.localize
import com.studcenter.features.authorization.domain.AuthorizationRepository
import com.studcenter.resources.MultiplatformResource
import dev.icerock.moko.network.generated.apis.AuthorizationApi
import dev.icerock.moko.network.generated.models.InlineObject

class AuthorizationRepositoryImpl(
    private val authorizationApi: AuthorizationApi,
) : AuthorizationRepository {
    override suspend fun login(email: String) {
        if (isCorrectEmail(email)) authorizationApi.loginUser(inlineObject = InlineObject(email = email))
        else throw Throwable(message = MultiplatformResource.strings.error.localize()) // TODO: Добавить вместо "error" -> "incorrect_email"
    }

    private fun isCorrectEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        return email.matches(regex)
    }
}