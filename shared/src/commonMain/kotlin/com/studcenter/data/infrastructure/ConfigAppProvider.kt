package com.studcenter.data.infrastructure

expect class ConfigAppProvider {
    val versionApp: String
    val deviceId: String
    var isFirstLaunch: Boolean
    val keyValueStorage: KeyValueStorage
}