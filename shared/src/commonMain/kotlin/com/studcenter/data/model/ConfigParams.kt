package com.studcenter.data.model

import com.studcenter.data.infrastructure.ConfigAppProvider
import com.studcenter.data.infrastructure.KeyValueStorage

data class ConfigParams(
    val keyValueStorage: KeyValueStorage,
    val configAppProvider: ConfigAppProvider,
)