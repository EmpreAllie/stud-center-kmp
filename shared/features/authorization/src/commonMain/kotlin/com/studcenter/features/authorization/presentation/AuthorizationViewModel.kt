package com.studcenter.features.authorization.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.model.CustomResponseException
import com.studcenter.data.utils.localize
import com.studcenter.features.authorization.domain.AuthorizationRepository
import com.studcenter.features.authorization.domain.exception.LoginException
import com.studcenter.features.authorization.domain.exception.PasswordException
import com.studcenter.resources.MultiplatformResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationViewModel(private val repository: AuthorizationRepository): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)
    val loginError: StateFlow<String?> = StateFlow(null)
    val passwordError: StateFlow<String?> = StateFlow(null)

    val isSuccessAuthorize: StateFlow<Boolean> = StateFlow(false)
    val newScreen: StateFlow<Screen?> = StateFlow(null)

    public fun login(login: String, password: String) {
        viewModelScope.launch {
            updateStateScreen(StateScreen.LOADING)

            try {
                val result = withContext(Dispatchers.IO) {
                    repository.login(login = login, password = password)
                }

                if (result) {
                    isSuccessAuthorize.update(value = true)
                } else {
                    throw Throwable(message = null)
                }

                updateStateScreen(StateScreen.DEFAULT)
            } catch (e: LoginException) {
                e.printStackTrace()

                loginError.update(value = e.message)
                updateStateScreen(StateScreen.DEFAULT)
            } catch (e: PasswordException) {
                e.printStackTrace()

                passwordError.update(value = e.message)
                updateStateScreen(StateScreen.DEFAULT)
            } catch (e: CustomResponseException) {
                e.printStackTrace()

                val descriptionTemplate = MultiplatformResource.strings.errorDescription_Template.localize()

                errorText.update(e.message?.ifEmpty { descriptionTemplate } ?: descriptionTemplate)
                updateStateScreen(StateScreen.DEFAULT)
            } catch (e: Throwable) {
                e.printStackTrace()

                errorText.update(MultiplatformResource.strings.errorDescription_Template.localize())
                updateStateScreen(StateScreen.DEFAULT)
            }
        }
    }
    public fun connectFirebase(token: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.fcmConnect(token)
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            
            newScreen.update(Screen.MENU)
        }
    }
    public fun clearErrorText() {
        errorText.update(value = null)
        loginError.update(value = null)
        passwordError.update(value = null)
    }
}