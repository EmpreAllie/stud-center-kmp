package com.studcenter.screen.authorization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.studcenter.features.authorization.presentation.AuthorizationViewModel
import com.studcenter.root.presentation.RootViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthorizationScreen(
    viewModel: AuthorizationViewModel = koinViewModel(),
    rootViewModel: RootViewModel = koinViewModel(),
) {
    val isSuccess by viewModel.isSuccessLogin.state.collectAsState()

    LaunchedEffect(isSuccess) {
        if (isSuccess) Unit// rootViewModel.updateScreen(screen = Screen.Confirm, isClear = false)
    }
    AuthorizationScreenContent(
        text = viewModel.email.state.collectAsState().value.orEmpty(),
        errorText = viewModel.errorText.state.collectAsState().value.orEmpty(),
        onTextChange = { newText ->
            viewModel.updateText(newText)
        }
    )
}