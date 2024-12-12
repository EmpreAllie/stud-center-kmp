package com.studcenter.data.infrastructure

import platform.Foundation.NSUUID
import platform.Foundation.NSUserDefaults

actual class ConfigAppProvider(actual val keyValueStorage: KeyValueStorage) {
    actual val versionApp: String
        get() = TODO("Not yet implemented")
    actual var isFirstLaunch: Boolean = keyValueStorage.firstLaunch.let { true }
    actual val deviceId: String = keyValueStorage.deviceId ?: generateDeviceId().also {
        keyValueStorage.deviceId = it
    }

    private fun generateDeviceId(): String {
        val defaults = NSUserDefaults.standardUserDefaults
        val storedId = defaults.stringForKey("deviceId")
        if (storedId != null) {
            return storedId
        }
        val newId = NSUUID().UUIDString
        defaults.setObject(newId, forKey = "deviceId")
        defaults.synchronize()
        return newId
    }
}