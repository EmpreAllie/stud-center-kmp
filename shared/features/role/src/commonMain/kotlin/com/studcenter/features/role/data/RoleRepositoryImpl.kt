package com.studcenter.features.role.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.features.role.domain.RoleRepository
import dev.icerock.moko.network.generated.apis.StudentApi

class RoleRepositoryImpl(private val configParams: ConfigParams, private val studentApi: StudentApi): RoleRepository {
    override suspend fun isWaitQueue(): Boolean {
        val configAppProvider = configParams.configAppProvider

        val studentQueueResponse = studentApi.apiV1StudentQueueGet(deviceId = configAppProvider.deviceId)

        return studentQueueResponse.passNumber?.value != null
    }
}