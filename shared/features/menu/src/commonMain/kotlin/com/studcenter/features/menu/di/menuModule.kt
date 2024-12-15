package com.studcenter.features.menu.di

import com.studcenter.features.menu.data.MenuRepositoryImpl
import com.studcenter.features.menu.domain.MenuRepository
import com.studcenter.features.menu.presentation.MenuViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val menuModule: Module = module {
    factory <MenuRepository> { MenuRepositoryImpl(
        configParams = get(),
        tablesApi = get(),
        queueApi = get(),
        shiftsApi = get()
    ) }
    factory { MenuViewModel(repository = get()) }
}