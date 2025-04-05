package com.studcenter.root

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.utils.Log
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.entity.enums.ErrorType
import com.studcenter.root.di.startKoin
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.screen.splash.SplashScreen
import com.studcenter.screen.authorization.AuthorizationScreen
import com.studcenter.ui.theme.MainTheme
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.logger.Level

class RootScreenApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RootScreenApplication)
            androidLogger(level = Level.DEBUG)
        }
    }
}

class RootActivity : ComponentActivity() {
    private lateinit var viewModel: RootViewModel

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = getKoin().get()
        globalApplicationContext = applicationContext

        setContent { KoinAndroidContext { RootApp(viewModel) } }
    }
}

@Composable
fun RootApp(viewModel: RootViewModel) {
    val navController = rememberNavController()

    val screen by viewModel.screen.state.collectAsState()

    LaunchedEffect(screen) {
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        if (currentDestination != Screen.SPLASH.toString() && screen == null) {
            navController.popBackStack()
            return@LaunchedEffect
        }
        val destination = screen?.toString()

        if (destination == null) {
            Log("RootApp", "Screen $screen not opened", errorType = ErrorType.ERROR)
            return@LaunchedEffect
        }

        if (currentDestination != destination) {
            if (viewModel.isClearStack) {
                navController.navigate(destination) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            } else {
                navController.navigate(destination)
            }
        }
    }

    MainTheme {
        NavHost(navController = navController, startDestination = Screen.SPLASH.toString()) {
            composable(Screen.SPLASH.toString()) {
                SplashScreen()
                BackHandler(true) {}
            }

            composable(Screen.AUTHORIZATION.toString()) {
                AuthorizationScreen()
                BackHandler(true) {}
            }
        }
    }
}