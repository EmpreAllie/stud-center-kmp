package com.studcenter.screen.role

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.features.role.presentation.RoleViewModel
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.B
import com.studcenter.ui.LoadingContent
import com.studcenter.ui.LoadingContentFull
import com.studcenter.ui.MainTheme
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun RoleScreen() {
    val viewModel: RoleViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val newScreen by viewModel.newScreen.state.collectAsState()
    val stateScreen by viewModel.stateScreen.state.collectAsState()

    LaunchedEffect(key1 = newScreen) {
        if (newScreen != null) {
            rootViewModel.updateScreen(
                screen = newScreen!!,
                argumentsJson = emptyList(),
                isClear = false
            )
        }
    }

    MainTheme {
        RoleScreenContent(onSetScreen = { newScreen ->
            if (newScreen == null) {
                rootViewModel.finishScreen()
            } else {
                viewModel.onSetScreen(screen = newScreen)
            }
        })

        if (stateScreen == StateScreen.LOADING) {
            LoadingContentFull()
        }
    }
}