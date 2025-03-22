package com.studcenter.features.authorization.di

import com.studcenter.features._screen.presentation.ScreenViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val authorizationModule: Module = module {
    factory {
        AuthorizationViewModel()
    }
}