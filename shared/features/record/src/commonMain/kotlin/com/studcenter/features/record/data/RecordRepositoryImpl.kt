package com.studcenter.features.record.data

import com.studcenter.data.model.ConfigParams
import com.studcenter.data.utils.localize
import com.studcenter.domain.utils.toNullable
import com.studcenter.features.record.domain.RecordRepository
import com.studcenter.resources.MultiplatformResource
import dev.icerock.moko.network.generated.apis.StudentApi
import dev.icerock.moko.network.generated.models.StudentQueueRequest

class RecordRepositoryImpl(private val configParams: ConfigParams, private val studentApi: StudentApi): RecordRepository {
    override suspend fun createRecord(
        firstName: String,
        lastName: String,
        middleName: String,
        group: String,
    ) {
        if (firstName.isEmpty() || lastName.isEmpty() || group.isEmpty()) {
            throw Throwable(message = MultiplatformResource.strings.fieldIsEmptyRecord.localize())
        }

        val deviceId = configParams.configAppProvider.deviceId
        val studentQueueRequest = StudentQueueRequest(
            firstName = firstName,
            lastName = lastName,
            deviceId = deviceId,
            middleName = middleName.toNullable(),
            studentGroup = group.toNullable()
        )
        studentApi.apiV1StudentQueuePost(
            studentQueueRequest = studentQueueRequest
        )
    }
}