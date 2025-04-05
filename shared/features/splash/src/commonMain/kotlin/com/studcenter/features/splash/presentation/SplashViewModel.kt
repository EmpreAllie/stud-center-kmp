package com.studcenter.features.splash.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.model.ConfigParams
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.features.splash.domain.SplashRepository
import com.studcenter.resources.MultiplatformResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SplashViewModel(private val splashRepository: SplashRepository): ViewModel() {
    val newScreen: StateFlow<Screen?> = StateFlow(null)
    val errorText: StateFlow<String?> = StateFlow(null)

    init {
        update()
    }

    private fun update() = viewModelScope.launch {
        try {
            if (!isActualVersionApp()) throw Throwable(MultiplatformResource.strings.versionOld.localize())
            val isAuthorized = withContext(Dispatchers.IO) {
                splashRepository.isAuthorized()
            }

            val screen =
                Screen.AUTHORIZATION // TODO: if (isAuthorized) Screen.MENU else Screen.AUTHORIZATION

            newScreen.update(value = screen)
        } catch (e: Throwable) {
            e.printStackTrace()

            errorText.update(MultiplatformResource.strings.errorDescription_Template.localize())
        }
    }

    private suspend fun isActualVersionApp() = withContext(Dispatchers.IO) {
        splashRepository.isActualVersionApp()
    }

    public fun clearErrorText() {
        errorText.update(null)
    }
}