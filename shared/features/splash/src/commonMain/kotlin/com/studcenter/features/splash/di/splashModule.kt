package com.studcenter.features.splash.di

import com.studcenter.features.splash.data.SplashRepositoryImpl
import com.studcenter.features.splash.domain.SplashRepository
import com.studcenter.features.splash.presentation.SplashViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val splashModule: Module = module {
    factory <SplashRepository> { SplashRepositoryImpl(configParams = get(), authenticationApi = get()) }
    factory { SplashViewModel(splashRepository = get()) }
}