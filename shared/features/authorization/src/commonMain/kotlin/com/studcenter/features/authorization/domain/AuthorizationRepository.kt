package com.studcenter.features.authorization.domain

interface AuthorizationRepository {
    suspend fun login(email: String)
}