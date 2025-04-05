package com.studcenter.features.authorization.di

import com.studcenter.features.authorization.data.AuthorizationRepositoryImpl
import com.studcenter.features.authorization.domain.AuthorizationRepository
import com.studcenter.features.authorization.presentation.AuthorizationViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authorizationModule: Module = module {
   /* factory <AuthorizationRepository> {
        AuthorizationRepositoryImpl(authorizationApi = get())
    } = factoryOf(::AuthorizationRepositoryImpl) bind AuthorizationRepository::class*/

    /*
    Single - Будет создан на всю программу один объект, то есть как "object"
    Factory - Каждый раз будет создаваться новый объект класса
     */
    factoryOf(::AuthorizationRepositoryImpl) bind AuthorizationRepository::class
    factoryOf(::AuthorizationViewModel)
}