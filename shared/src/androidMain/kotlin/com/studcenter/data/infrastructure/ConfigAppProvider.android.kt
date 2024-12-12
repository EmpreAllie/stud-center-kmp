package com.studcenter.data.infrastructure

import android.content.Context
import com.studcenter.BuildConfig
import java.util.UUID

actual class ConfigAppProvider(actual val keyValueStorage: KeyValueStorage) {
    actual val versionApp: String = BuildConfig.VERSION_NAME
    actual var isFirstLaunch: Boolean = keyValueStorage.firstLaunch.let { true }
    actual val deviceId: String = keyValueStorage.deviceId ?: UUID.randomUUID().toString().also {
        keyValueStorage.deviceId = it
    }
}