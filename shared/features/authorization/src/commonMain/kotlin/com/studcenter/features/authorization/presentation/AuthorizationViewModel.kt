package com.studcenter.features.authorization.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel

class AuthorizationViewModel(): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)

    init {

    }
}