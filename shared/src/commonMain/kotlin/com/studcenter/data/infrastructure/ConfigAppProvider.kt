package com.studcenter.data.infrastructure

expect class ConfigAppProvider {
    val versionApp: String
    var isFirstLaunch: Boolean
    val keyValueStorage: KeyValueStorage
}