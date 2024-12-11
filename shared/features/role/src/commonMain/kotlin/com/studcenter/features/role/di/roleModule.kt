package com.studcenter.features.role.di

import com.studcenter.features.role.presentation.RoleViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val roleModule: Module = module {
    factory {
        RoleViewModel()
    }
}