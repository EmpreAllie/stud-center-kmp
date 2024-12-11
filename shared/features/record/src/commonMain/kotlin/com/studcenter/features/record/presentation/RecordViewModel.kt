package com.studcenter.features.record.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class RecordViewModel(): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)

    init {

    }
}