package com.studcenter.data.infrastructure

import com.studcenter.BuildConfig


actual class ConfigAppProvider(actual val keyValueStorage: KeyValueStorage) {
    actual val versionApp: String
        get() = BuildConfig.VERSION_NAME
    actual var isFirstLaunch: Boolean = keyValueStorage.firstLaunch.let { true }
}