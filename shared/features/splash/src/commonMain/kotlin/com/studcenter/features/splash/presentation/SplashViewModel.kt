package com.studcenter.features.splash.presentation

import com.studcenter.base.features.StateFlow
import com.studcenter.base.features.ViewModel
import com.studcenter.data.model.ConfigParams


class SplashViewModel(val configParams: ConfigParams): ViewModel() {
    val errorText: StateFlow<String?> = StateFlow(null)
}