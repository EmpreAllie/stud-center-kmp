package com.studcenter.features.role.di

import com.studcenter.features.role.data.RoleRepositoryImpl
import com.studcenter.features.role.domain.RoleRepository
import com.studcenter.features.role.presentation.RoleViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val roleModule: Module = module {
    factory <RoleRepository> { RoleRepositoryImpl(configParams = get(), studentApi = get()) }
    factory { RoleViewModel(roleRepository = get()) }
}