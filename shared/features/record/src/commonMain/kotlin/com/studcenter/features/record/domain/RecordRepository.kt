package com.studcenter.features.record.domain

interface RecordRepository {
    suspend fun createRecord(
        firstName: String,
        lastName: String,
        middleName: String,
        group: String
    )
}