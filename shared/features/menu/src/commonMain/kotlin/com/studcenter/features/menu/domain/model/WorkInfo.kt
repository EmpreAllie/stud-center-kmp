package com.studcenter.features.menu.domain.model

data class WorkInfo(
    val currentNumber: Int? = null,
    val firstName: String,
    val lastName: String,
    val middleName: String?,
    val group: String,
    val inQueueNumber: Int
)
