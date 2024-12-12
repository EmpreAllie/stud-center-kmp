package com.studcenter.features.record.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class RecordViewModel(): ViewModel() {
    val firstNameError: StateFlow<String?> = StateFlow(null)
    val middleNameError: StateFlow<String?> = StateFlow(null)
    val lastNameError: StateFlow<String?> = StateFlow(null)
    val groupError: StateFlow<String?> = StateFlow(null)

    public fun createRecord(firstName: String, lastName: String, middleName: String, group: String) {

    }


    public fun clearErrorText() {
        firstNameError.update(null)
        middleNameError.update(null)
        lastNameError.update(null)
        groupError.update(null)
    }
}