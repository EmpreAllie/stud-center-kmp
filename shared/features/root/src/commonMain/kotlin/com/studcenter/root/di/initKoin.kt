package com.studcenter.root.di

import com.studcenter.di.networkModule
import com.studcenter.features.splash.di.splashModule
import com.studcenter.features.authorization.di.authorizationModule
import org.koin.dsl.KoinAppDeclaration

public fun startKoin(koinAppDeclaration: KoinAppDeclaration) {
    org.koin.core.context.startKoin {
        koinAppDeclaration()
        modules(rootModule, networkModule, splashModule, authorizationModule)
    }
}