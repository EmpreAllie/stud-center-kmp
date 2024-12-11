package com.studcenter.features._screen.di

import com.studcenter.features._screen.presentation.ScreenViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val screenModule: Module = module {
    factory {
        ScreenViewModel()
    }
}