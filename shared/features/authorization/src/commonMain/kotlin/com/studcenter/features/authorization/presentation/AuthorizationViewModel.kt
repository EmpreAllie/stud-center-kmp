package com.studcenter.features.authorization.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.model.CustomResponseException
import com.studcenter.features.authorization.domain.AuthorizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationViewModel(
    val repository: AuthorizationRepository,
) : ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)
    val email: StateFlow<String?> = StateFlow("")
    val isSuccessLogin: StateFlow<Boolean> = StateFlow(false)

    public fun sendEmail() = viewModelScope.launch {
        try {
            updateStateScreen(StateScreen.LOADING)

            withContext(Dispatchers.IO) {
                repository.login(email = email.getValue().orEmpty())
            }

            isSuccessLogin.update(true)
        } catch (e: CustomResponseException) {
            errorText.update(e.responseMessage)
        } catch (e: Throwable) {
            errorText.update(e.message)
        }

        updateStateScreen(StateScreen.DEFAULT)
    }

    public fun clearText() {
        errorText.update(null)
    }

    public fun updateText(newText: String) {
        val builder = StringBuilder(newText)
        // TODO: обработка

        email.update(builder.toString())
        clearText()
    }
}