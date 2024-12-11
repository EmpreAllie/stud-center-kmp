package com.studcenter.features.role.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class RoleViewModel(): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)

    init {

    }
}