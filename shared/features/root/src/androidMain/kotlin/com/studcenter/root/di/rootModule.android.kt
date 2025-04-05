package com.studcenter.root.di

import android.content.Context
import com.studcenter.data.infrastructure.ConfigAppProvider
import com.studcenter.data.infrastructure.KeyValueStorage
import com.studcenter.data.model.ConfigParams
import com.studcenter.root.presentation.RootViewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val rootModule: Module = module {
    single<Settings> {
        SharedPreferencesSettings(
            androidContext().getSharedPreferences("app", Context.MODE_PRIVATE)
        )
    }

    singleOf(::KeyValueStorage)
    singleOf(::ConfigAppProvider)
    singleOf(::ConfigParams)
    singleOf(::RootViewModel)
}