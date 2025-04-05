package com.studcenter.features.splash.domain

interface SplashRepository {
    suspend fun isActualVersionApp(): Boolean
    suspend fun isAuthorized(): Boolean
}