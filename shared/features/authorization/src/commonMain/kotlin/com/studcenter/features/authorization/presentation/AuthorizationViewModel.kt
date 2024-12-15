package com.studcenter.features.authorization.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.features.authorization.domain.AuthorizationRepository
import kotlin.math.log

class AuthorizationViewModel(private val repository: AuthorizationRepository): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)
    val loginError: StateFlow<String?> = StateFlow(null)
    val passwordError: StateFlow<String?> = StateFlow(null)

    val isSuccessAuthorize: StateFlow<Boolean> = StateFlow(false)

    init {

    }

    public fun login(login: String, password: String) {

    }

    public fun clearErrorText() {
        errorText.update(value = null)
        loginError.update(value = null)
        passwordError.update(value = null)
    }
}