package com.studcenter.features.menu.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class MenuViewModel(): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)

    init {

    }
}