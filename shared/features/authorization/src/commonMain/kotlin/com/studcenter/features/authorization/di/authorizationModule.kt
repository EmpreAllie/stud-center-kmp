package com.studcenter.features.authorization.di

import com.studcenter.features.authorization.data.AuthorizationRepositoryImpl
import com.studcenter.features.authorization.domain.AuthorizationRepository
import com.studcenter.features.authorization.presentation.AuthorizationViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val authorizationModule: Module = module {
    factory <AuthorizationRepository> { AuthorizationRepositoryImpl(
        configParams = get(),
        authenticationApi = get()
    ) }
    factory {
        AuthorizationViewModel(
            repository = get()
        )
    }
}