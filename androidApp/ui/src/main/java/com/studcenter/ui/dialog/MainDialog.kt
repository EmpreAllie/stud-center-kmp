package com.studcenter.ui.dialog

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.MainButton

/**
 * Диалоговое окно при появлении ошибки
 * @param title Главный текст
 * @param description Описание ошибки
 * @param confirmText Текст кнопки подтверждения
 * @param confirmAction Действие при подтверждении
 * @param cancelAction Действие при закрытии
 * @sample MainDialogConfirmLight_Preview
 */
@Composable
fun MainDialog(
    title: String,
    description: String,
    confirmText: String,
    confirmAction: () -> Unit = {},
    cancelAction: () -> Unit,
) {
    Dialog(
        onDismissRequest = cancelAction,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .border(2.dp, B.colors().secondary, RoundedCornerShape(100.dp))
                        .background(B.colors().white)
                        .clickable(onClick = cancelAction),
                    painter = painterResource(id = MultiplatformResource.images.ic_cross.drawableResId),
                    tint = B.colors().secondary,
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp)
                    .shadow(12.dp, RoundedCornerShape(12.dp))
                    .background(B.colors().gray)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = title,
                    style = B.typography().text.title,
                    color = B.colors().secondary
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 32.dp),
                    text = description,
                    style = B.typography().text.main,
                    color = B.colors().secondary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    MainButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentColor = B.colors().secondary,
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        text = confirmText,
                        textStyle = B.typography().text.main,
                        backgroundColor = B.colors().white,
                        cornerShapeSize = 100.dp,
                        onClick = confirmAction
                    )
                }

            }
        }
    }
}

@Preview(name = "Light Mode")
@Composable
internal fun MainDialogConfirmLight_Preview() {
    globalApplicationContext = LocalContext.current
    MainTheme {
        Box(
            modifier = Modifier
                .background(B.colors().white.copy(alpha = 0.3f))
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MainDialog(
                title = MultiplatformResource.strings.tableSelect.localize(),
                description = MultiplatformResource.strings.tableAssign.localize(),
                confirmText = MultiplatformResource.strings.accept.localize(),
                confirmAction = { /*TODO*/ },
                cancelAction = { /* TODO */})
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun MainDialogConfirmDark_Preview() {
    globalApplicationContext = LocalContext.current
    MainTheme {
        Box(
            modifier = Modifier
                .background(B.colors().white.copy(alpha = 0.3f))
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            MainDialog(
                title = MultiplatformResource.strings.tableSelect.localize(),
                description = MultiplatformResource.strings.tableAssign.localize(),
                confirmText = MultiplatformResource.strings.accept.localize(),
                confirmAction = { /*TODO*/ },
                cancelAction = { /* TODO */})
        }
    }
}