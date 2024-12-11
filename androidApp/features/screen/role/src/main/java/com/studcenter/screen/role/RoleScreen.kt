package com.studcenter.screen.role

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.MainTheme
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun RoleScreen() {
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    MainTheme {
        RoleScreenContent(onSetScreen = { newScreen ->
            if (newScreen == null) {
                rootViewModel.finishScreen()
            } else {
                rootViewModel.updateScreen(
                    screen = newScreen,
                    argumentsJson = emptyList(),
                    isClear = false
                )
            }
        })
    }
}