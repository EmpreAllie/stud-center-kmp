package com.studcenter.features._screen.di

import com.studcenter.features._screen.data.ScreenRepositoryImpl
import com.studcenter.features._screen.domain.ScreenRepository
import com.studcenter.features._screen.presentation.ScreenViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.dsl.bind

val screenModule: Module = module {
    factoryOf(::ScreenRepositoryImpl) bind ScreenRepository::class
    factoryOf(::ScreenViewModel)
}