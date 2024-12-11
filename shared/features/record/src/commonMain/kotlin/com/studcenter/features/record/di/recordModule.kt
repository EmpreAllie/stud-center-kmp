package com.studcenter.features.record.di

import com.studcenter.features.record.presentation.RecordViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val recordModule: Module = module {
    factory {
        RecordViewModel()
    }
}