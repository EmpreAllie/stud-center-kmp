package com.studcenter.screen.record

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.studcenter.features.record.presentation.RecordViewModel
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.MainTheme
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun RecordScreen() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity ?: throw IllegalStateException("Context is not an instance of ComponentActivity")

    val viewModel: RecordViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val firstName by remember { mutableStateOf("") }
    val lastName by remember { mutableStateOf("") }
    val middleName by remember { mutableStateOf("") }
    val group by remember { mutableStateOf("") }

    MainTheme {
        RecordScreenContent(
            firstName = firstName,
            firstNameError = viewModel.firstNameError.state.collectAsState().value,
            lastName = lastName,
            lastNameError = viewModel.lastNameError.state.collectAsState().value,
            middleName = middleName,
            middleNameError = viewModel.middleNameError.state.collectAsState().value,
            group = group,
            groupError = viewModel.groupError.state.collectAsState().value,
            onClearError = viewModel::clearErrorText,
            onAccept = {
                viewModel.createRecord(
                    firstName = firstName,
                    lastName = lastName,
                    middleName = middleName,
                    group = group
                )
            },
            onSetScreen = { newScreen ->
                if (newScreen != null) {
                    rootViewModel.updateScreen(
                        screen = newScreen,
                        argumentsJson = emptyList(),
                        isClear = true
                    )
                } else {
                    rootViewModel.finishScreen()
                }
            },
        )
    }
}