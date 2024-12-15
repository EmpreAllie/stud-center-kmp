package com.studcenter.screen.menu

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.studcenter.base.features.enum.StateScreen
import com.studcenter.data.utils.format
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.features.menu.presentation.MenuViewModel
import com.studcenter.resources.MultiplatformResource
import com.studcenter.root.presentation.RootViewModel
import com.studcenter.screen.menu.navigation.DrawerContent
import com.studcenter.ui.B
import com.studcenter.ui.LoadingContentFull
import com.studcenter.ui.MainTheme
import com.studcenter.ui.dialog.DialogError
import com.studcenter.ui.dialog.MainDialog
import dev.icerock.moko.resources.format
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin

@Composable
fun MenuScreen() {
    val context = LocalContext.current

    val viewModel: MenuViewModel = remember { getKoin().get() }
    val rootViewModel: RootViewModel = remember { getKoin().get() }

    val stateScreen by viewModel.stateScreen.state.collectAsState()
    val errorText by viewModel.errorText.state.collectAsState()
    val isWork by viewModel.isWork.state.collectAsState()
    val tableId by viewModel.tableId.state.collectAsState()

    var isCloseShift by remember { mutableStateOf(false) }
    var isExitAccount by remember { mutableStateOf(false) }
    var isOpenTable by remember { mutableStateOf(false) }
    var isNext by remember { mutableStateOf(false) }

    val drawerState: DrawerState by remember { mutableStateOf(DrawerState(initialValue = DrawerValue.Closed)) }

    MainTheme {
        ModalNavigationDrawer(
            modifier = Modifier.fillMaxSize(),
            drawerContent = {
                DrawerContent(
                    isWork = isWork,
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
                    onCloseShifts = {
                        isCloseShift = true
                    },
                    onExit = {
                        isExitAccount = true
                    }
                )
            },
            drawerState = drawerState,
            scrimColor = B.colors().black.copy(0.3f),
        ) {
            MenuScreenContent(
                isWork = isWork,
                workInfo = viewModel.workInfo.state.collectAsState().value,
                tableItems = viewModel.tableItems.state.collectAsState().value,
                onClickTable = { id ->
                    viewModel.rememberTableId(id = id)
                    isOpenTable = true
                },
                tableFormatted = viewModel.tableFormatted.state.collectAsState().value,
                onOpenMenu = {
                    val newDrawerValue = if (drawerState.isOpen) {
                        DrawerValue.Closed
                    } else {
                        DrawerValue.Open
                    }

                    CoroutineScope(Dispatchers.Main).launch {
                        drawerState.snapTo(targetValue = newDrawerValue)
                    }
                },
                onNext = {
                    isNext = true
                }
            )
        }

        if (!errorText.isNullOrBlank()) {
            DialogError(
                title = MultiplatformResource.strings.errorTitle.localize(),
                description = errorText
                    ?: MultiplatformResource.strings.errorDescription_Template.localize()
            ) {
                viewModel.clearTextError()
            }
        }

        if (isCloseShift) {
            MainDialog(
                title = MultiplatformResource.strings.closeShifts.localize(),
                description = MultiplatformResource.strings.closeShifts_Description.localize(),
                confirmText = MultiplatformResource.strings.confirm.localize(),
                confirmAction = {
                    isCloseShift = false
                    viewModel.closeShift()
                },
                cancelAction = {
                    isCloseShift = false
                }
            )
        }

        if (isExitAccount) {
            MainDialog(
                title = MultiplatformResource.strings.exitAccount_Title.localize(),
                description = MultiplatformResource.strings.exitAccount_Description.localize(),
                confirmText = MultiplatformResource.strings.confirm.localize(),
                confirmAction = {
                    isExitAccount = false
                    viewModel.exitAccount()
                },
                cancelAction = {
                    isExitAccount = false
                }
            )
        }

        if (isNext) {
            MainDialog(
                title = MultiplatformResource.strings.nextQueue.localize(),
                description = MultiplatformResource.strings.nextQueue_Description.localize(),
                confirmText = MultiplatformResource.strings.accept.localize(),
                confirmAction = {
                    isNext = false
                    viewModel.nextQueue()
                },
                cancelAction = {
                    isNext = false
                }
            )
        }

        if (isOpenTable) {
            MainDialog(
                title = MultiplatformResource.strings.tableSelect.localize(),
                description = MultiplatformResource.strings.tableAssign.format("$tableId"),
                confirmText = MultiplatformResource.strings.accept.localize(),
                confirmAction = {
                    isOpenTable = false
                    viewModel.openShift()
                },
                cancelAction = {
                    isOpenTable = false
                }
            )
        }

        if (!errorText.isNullOrBlank()) {
            DialogError(
                title = MultiplatformResource.strings.errorTitle.localize(),
                description = errorText
                    ?: MultiplatformResource.strings.errorDescription_Template.localize()
            ) {
                viewModel.clearTextError()
            }
        }

        if (stateScreen == StateScreen.LOADING) {
            LoadingContentFull()
        }
    }
}