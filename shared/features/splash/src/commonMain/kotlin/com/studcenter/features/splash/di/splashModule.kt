package com.studcenter.features.splash.di

import com.studcenter.features.splash.data.SplashRepositoryImpl
import com.studcenter.features.splash.domain.SplashRepository
import com.studcenter.features.splash.presentation.SplashViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val splashModule: Module = module {
    factoryOf(::SplashRepositoryImpl) bind SplashRepository::class
    factoryOf(::SplashViewModel)
}