package com.studcenter.screen.authorization

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.InputTextField
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.MainButton
import com.studcenter.ui.button.TextButton

@Composable
fun AuthorizationScreenContent() {
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomAppBar(
                containerColor = B.colors().white,
                contentColor = B.colors().white
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(
                        modifier = Modifier,
                        text = MultiplatformResource.strings.callService.localize(),
                        textStyle = B.typography().text.main,
                        textColor = B.colors().primary
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
                    .background(B.colors().white),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = MultiplatformResource.strings.authorization.localize(),
                    style = B.typography().text.title,
                    color = B.colors().primary
                )

                InputTextField(
                    modifier = Modifier
                        .padding(top = 50.dp, bottom = 5.dp, start = 25.dp, end = 25.dp),
                    hintText = MultiplatformResource.strings.mailPrompt.localize(),
                    errorText = "",
                    isError = false,
                    isEnabled = true,
                    isSingleLine = true,
                    maxLength = 16
                    /*
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {

                                },
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = null
                        )
                    }
                     */
                )

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp), // отступ по краям
                    text = MultiplatformResource.strings.enter.localize(),
                    textStyle = B.typography().text.buttonText,
                    contentColor = B.colors().white,
                    backgroundColor = B.colors().primary,
                    contentPadding = PaddingValues(vertical = 25.dp) // отступ от текста в кнопке
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
    MainTheme {
        AuthorizationScreenContent()
    }
}