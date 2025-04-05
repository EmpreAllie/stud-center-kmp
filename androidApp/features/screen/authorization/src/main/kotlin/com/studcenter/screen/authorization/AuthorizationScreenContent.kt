package com.studcenter.screen.authorization

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.theme.B
import com.studcenter.ui.InputTextField
import com.studcenter.ui.theme.MainTheme
import com.studcenter.ui.button.MainButton
import com.studcenter.ui.button.TextButton

@Composable
fun AuthorizationScreenContent(
    text: String,
    errorText: String,
    onTextChange: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomAppBar(
                containerColor = B.colors.white,
                contentColor = B.colors.white
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        modifier = Modifier,
                        text = MultiplatformResource.strings.callService.localize(),
                        textStyle = B.typography.common.main,
                        textColor = B.colors.primary
                    ) {
                        // логика для нажатия
                    }
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .background(B.colors.white),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = MultiplatformResource.strings.authorization.localize(),
                    style = B.typography.common.title,
                    color = B.colors.primary
                )

                InputTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = MultiplatformResource.strings.mailPrompt.localize(),
                    text = text,
                    isEnabled = true,
                    errorText = errorText,
                    isSingleLine = true,
                    onTextChange = onTextChange
                )

                MainButton(
                    modifier = Modifier,
                    text = MultiplatformResource.strings.enter.localize(),
                ) {
                    // по идее тут логика для onClick
                }

            }
        }
    )
}

@Composable
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
internal fun AuthorizationScreenContent_Preview() {
    globalApplicationContext = LocalContext.current
    var text by remember { mutableStateOf("") }
    MainTheme {
        AuthorizationScreenContent(
            text = text,
            errorText = "",
            onTextChange = { text = it }
        )
    }
}