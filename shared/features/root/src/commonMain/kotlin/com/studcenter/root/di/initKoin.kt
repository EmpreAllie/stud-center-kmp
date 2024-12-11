package com.studcenter.root.di

import com.studcenter.di.networkModule
import com.studcenter.features.authorization.di.authorizationModule
import com.studcenter.features.display.di.displayModule
import com.studcenter.features.menu.di.menuModule
import com.studcenter.features.record.di.recordModule
import com.studcenter.features.role.di.roleModule
import com.studcenter.features.splash.di.splashModule
import org.koin.dsl.KoinAppDeclaration

public fun startKoin(koinAppDeclaration: KoinAppDeclaration) {
    org.koin.core.context.startKoin {
        koinAppDeclaration()
        modules(rootModule, networkModule, splashModule, authorizationModule, displayModule, menuModule, recordModule, roleModule)
    }
}