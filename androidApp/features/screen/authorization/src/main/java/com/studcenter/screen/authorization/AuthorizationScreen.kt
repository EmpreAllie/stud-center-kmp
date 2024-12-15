package com.studcenter.screen.authorization

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.messaging.FirebaseMessaging
import com.studcenter.base.features.enum.Screen
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.utils.Log
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.features.authorization.presentation.AuthorizationViewModel
import com.studcenter.resources.MultiplatformResource
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.LoadingContentFull
import com.studcenter.ui.MainTheme
import com.studcenter.ui.dialog.DialogError
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun AuthorizationScreen() {
    val context = LocalContext.current

    val viewModel: AuthorizationViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val stateScreen by viewModel.stateScreen.state.collectAsState()
    val errorText by viewModel.errorText.state.collectAsState()
    val isSuccessAuthorize by viewModel.isSuccessAuthorize.state.collectAsState()
    val newScreen by viewModel.newScreen.state.collectAsState()

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(isSuccessAuthorize) {
        if (isSuccessAuthorize) {
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val token = task.result
                        Log("FCM", "FCM Token: $token")
                        viewModel.connectFirebase(token = token)
                    } else {
                        task.exception?.printStackTrace()
                    }
                }
        }
    }

    LaunchedEffect(newScreen) {
        if (newScreen != null) {
            rootViewModel.updateScreen(
                screen = newScreen!!,
                argumentsJson = emptyList(),
                isClear = true
            )
        }
    }

    MainTheme {
        AuthorizationScreenContent(
            login = login,
            password = password,
            loginError = viewModel.loginError.state.collectAsState().value,
            passwordError = viewModel.passwordError.state.collectAsState().value,
            onSetLogin = { value ->
                login = value
                viewModel.clearErrorText()
            },
            onSetPassword = { value ->
                password = value
                viewModel.clearErrorText()
            },
            onLogin = {
                viewModel.login(
                    login = login,
                    password = password
                )
            },
            onCallService = {
                val mail = Constants.Strings.SYSTEM.mailSupport
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("mailto:$mail")
                try {
                    context.startActivity(dialIntent)
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Непредвиденная ошибка",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            onSetScreen = { newScreen ->
                if (newScreen == null) {
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