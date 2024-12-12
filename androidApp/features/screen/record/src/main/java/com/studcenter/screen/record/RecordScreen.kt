package com.studcenter.screen.record

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.studcenter.base.features.enum.Screen
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.utils.localize
import com.studcenter.features.record.presentation.RecordViewModel
import com.studcenter.resources.MultiplatformResource
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.LoadingContentFull
import com.studcenter.ui.MainTheme
import com.studcenter.ui.dialog.DialogError
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun RecordScreen() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity ?: throw IllegalStateException("Context is not an instance of ComponentActivity")

    val viewModel: RecordViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val errorText by viewModel.errorText.state.collectAsState()
    val stateScreen by viewModel.stateScreen.state.collectAsState()
    val isCreatedRecord by viewModel.isCreatedRecord.state.collectAsState()

    val firstName by remember { mutableStateOf("") }
    val lastName by remember { mutableStateOf("") }
    val middleName by remember { mutableStateOf("") }
    val group by remember { mutableStateOf("") }

    LaunchedEffect(key1 = isCreatedRecord) {
        if (isCreatedRecord) {
            rootViewModel.updateScreen(
                screen = Screen.DISPLAY,
                argumentsJson = emptyList(),
                isClear = false
            )
        }
    }

    MainTheme {
        RecordScreenContent(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            group = group,
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