package com.studcenter.features.display.di

import com.studcenter.features.display.presentation.DisplayViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val displayModule: Module = module {
    factory {
        DisplayViewModel()
    }
}