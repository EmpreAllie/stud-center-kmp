package com.studcenter.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.TextButton

@Composable
fun DialogError(
    title: String,
    description: String,
    onClose: () -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(horizontal = 36.dp, vertical = 16.dp)
                    .shadow(10.dp)
                    .fillMaxWidth()
                    .background(B.colors().white, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = B.typography().text.title,
                        color = B.colors().secondary,
                        modifier = Modifier.padding(bottom = 12.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = description,
                        style = B.typography().text.main,
                        color = B.colors().secondary,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            text = MultiplatformResource.strings.close.localize(),
                            normalTextColor = B.colors().primary,
                            onClick = onClose
                        )
                    }
                }
            }

        }

    }
}


@Preview
@Composable
internal fun DialogError_Preview() {
    globalApplicationContext = LocalContext.current
    
    MainTheme {
        Box(modifier = Modifier.background(B.colors().white)) {
            DialogError(
                title = MultiplatformResource.strings.errorTitle.localize(),
                description = "Описание в две строки, Описание в две строки"
            ) {

            }
        }
    }
}