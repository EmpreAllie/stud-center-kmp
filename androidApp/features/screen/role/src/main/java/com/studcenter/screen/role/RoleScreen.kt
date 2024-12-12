package com.studcenter.screen.role

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.studcenter.features.role.presentation.RoleViewModel
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.MainTheme
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun RoleScreen() {
    val viewModel: RoleViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val newScreen by viewModel.newScreen.state.collectAsState()

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
    }
}