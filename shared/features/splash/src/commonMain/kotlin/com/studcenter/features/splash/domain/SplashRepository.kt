package com.studcenter.features.splash.domain

interface SplashRepository {
    public suspend fun isAuthorized(): Boolean
}