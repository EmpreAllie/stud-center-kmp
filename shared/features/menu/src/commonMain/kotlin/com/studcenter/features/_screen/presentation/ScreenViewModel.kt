package com.studcenter.features.screen.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class ScreenViewModel(): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)

    init {

    }
}