package com.studcenter.screen.display

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.utils.localize
import com.studcenter.features.display.presentation.DisplayViewModel
import com.studcenter.resources.MultiplatformResource
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.LoadingContentFull
import com.studcenter.ui.MainTheme
import com.studcenter.ui.dialog.DialogError
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun DisplayScreen() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity ?: throw IllegalStateException("Context is not an instance of ComponentActivity")

    val viewModel: DisplayViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val stateScreen by viewModel.stateScreen.state.collectAsState()
    val errorText by viewModel.errorText.state.collectAsState()
    val isCloseScreen by viewModel.isCloseScreen.state.collectAsState()

    LaunchedEffect(isCloseScreen) {
        if (isCloseScreen) {
            rootViewModel.finishScreen()
        }
    }

    MainTheme {
        DisplayScreenContent(
            positionFormatted = viewModel.positionFormatted.state.collectAsState().value,
            statusFormatted = viewModel.statusFormatted.state.collectAsState().value,
            onSetScreen = { newScreen ->
                if (newScreen == null) {
                    viewModel.stop()
                    rootViewModel.finishScreen()
                } else {
                    rootViewModel.updateScreen(
                        screen = newScreen,
                        argumentsJson = emptyList(),
                        isClear = false
                    )
                }
            }
        )

        if (!errorText.isNullOrBlank()) {
            DialogError(
                title = MultiplatformResource.strings.errorTitle.localize(),
                description = errorText ?: MultiplatformResource.strings.errorDescription_Template.localize()
            ) {
                viewModel.clearErrorText()
            }
        }

        if (stateScreen == StateScreen.LOADING) {
            LoadingContentFull()
        }
    }
}