package com.studcenter.features.authorization.domain

interface AuthorizationRepository {
    public suspend fun login(login: String, password: String): Boolean
    public suspend fun fcmConnect(token: String)
}