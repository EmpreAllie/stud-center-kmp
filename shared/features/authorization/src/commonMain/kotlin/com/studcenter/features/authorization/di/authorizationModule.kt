package com.studcenter.features.authorization.di

import com.studcenter.features.authorization.presentation.AuthorizationViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val authorizationModule: Module = module {
    factory {
        AuthorizationViewModel()
    }
}