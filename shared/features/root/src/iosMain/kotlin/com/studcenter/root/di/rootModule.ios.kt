package com.studcenter.root.di


import com.studcenter.data.infrastructure.ConfigAppProvider
import com.studcenter.data.infrastructure.KeyValueStorage
import com.studcenter.data.model.ConfigParams
import com.studcenter.root.presentation.RootViewModel
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

actual val rootModule: Module = module {
    single<Settings> {
        NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
    }

    single { KeyValueStorage(settings = get()) }
    single { ConfigAppProvider(keyValueStorage = get()) }
    single { ConfigParams(keyValueStorage = get(), configAppProvider = get()) }

    single {
        RootViewModel()
    }
}