package com.studcenter.screen.***

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.studcenter.features.screen.presentation.ScreenViewModel
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.MainTheme
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin
import org.koin.androidx.compose.koinViewModel

@Composable
fun Screen(
    viewModel: ScreenViewModel = koinViewModel(),
    rootViewModel: RootViewModel = koinViewModel(),
) {
    val errorText by viewModel.errorText.state.collectAsState()

    LaunchedEffect(errorText) {
        when {
            errorText != null -> {

            }
        }
    }

    ScreenContent()
}