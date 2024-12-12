package com.studcenter.features.splash.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.model.ConfigParams
import com.studcenter.domain.constants.Constants
import com.studcenter.features.splash.domain.SplashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SplashViewModel(private val splashRepository: SplashRepository): ViewModel() {
    val newScreen: StateFlow<Screen?> = StateFlow(null)

    init {
        update()
    }

    public fun update() {
        viewModelScope.launch {
            val isAuthorized = withContext(Dispatchers.IO) {
                delay(Constants.Numbers.delaySplash)
                splashRepository.isAuthorized()
            }

            val screen = if (isAuthorized) Screen.MENU else Screen.ROLE

            newScreen.update(value = screen)
        }
    }
}