package com.studcenter.screen.splash

import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.studcenter.data.utils.Log
import com.studcenter.data.utils.localize
import com.studcenter.features.splash.presentation.SplashViewModel
import com.studcenter.resources.MultiplatformResource
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.ui.MainTheme
import com.studcenter.ui.dialog.DialogError
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
        ?: throw IllegalStateException("Context is not an instance of ComponentActivity")

    val viewModel: SplashViewModel = remember { getKoin().get { parametersOf(activity) } }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val newScreen by viewModel.newScreen.state.collectAsState()
    val errorText by viewModel.errorText.state.collectAsState()

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.update()
        } else {
            Toast.makeText(context, MultiplatformResource.strings.notificationDisabled.localize(), Toast.LENGTH_SHORT).show()
            activity.finish()
        }
    }

    LaunchedEffect(Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        } else {
            viewModel.update()
        }
    }

    LaunchedEffect(newScreen) {
        if (newScreen != null) {
            rootViewModel.updateScreen(
                newScreen!!,
                emptyList(),
                true
            )
        }
    }

    MainTheme {
        SplashScreenContent()

        if (!errorText.isNullOrBlank()) {
            DialogError(
                title = MultiplatformResource.strings.errorTitle.localize(),
                description = errorText ?: MultiplatformResource.strings.errorDescription_Template.localize()
            ) {
                activity.finish()
            }
        }
    }
}