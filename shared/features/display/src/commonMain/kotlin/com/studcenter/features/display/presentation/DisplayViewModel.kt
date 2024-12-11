package com.studcenter.features.screen.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class DisplayViewModel(): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)

    init {

    }
}