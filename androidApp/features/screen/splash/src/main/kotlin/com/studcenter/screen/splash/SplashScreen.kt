package com.studcenter.screen.splash

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.studcenter.data.utils.localize
import com.studcenter.features.splash.presentation.SplashViewModel
import com.studcenter.resources.MultiplatformResource
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.dialog.DialogError
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    rootViewModel: RootViewModel = koinViewModel(),
) {
    val activity = LocalActivity.current

    val newScreen by viewModel.newScreen.state.collectAsState()
    val errorText by viewModel.errorText.state.collectAsState()

    LaunchedEffect(newScreen) {
        if (newScreen != null) {
            rootViewModel.updateScreen(
                screen = newScreen!!,
                argumentsJson = emptyList(),
                isClear = true
            )
        }
    }

    SplashScreenContent()

    if (!errorText.isNullOrBlank()) {
        DialogError(
            title = MultiplatformResource.strings.errorTitle.localize(),
            description = errorText
                ?: MultiplatformResource.strings.errorDescription_Template.localize()
        ) {
            activity?.finish()
        }
    }
}