package com.studcenter.screen.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.InputTextField
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.ImageButton
import com.studcenter.ui.button.MainButton
import com.studcenter.ui.button.TextButton

@Composable
fun AuthorizationScreenContent(
    login: String,
    loginError: String?,
    password: String,
    passwordError: String?,
    onSetLogin: (String) -> Unit,
    onSetPassword: (String) -> Unit,
    onLogin: () -> Unit,
    onCallService: () -> Unit,
    onSetScreen: (Screen?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(B.colors().primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ImageButton(
                modifierIcon = Modifier,
                iconId = MultiplatformResource.images.ic_back.drawableResId,
                iconColor = B.colors().white
            ) {
                onSetScreen(null)
            }
        }

        Spacer(modifier = Modifier.weight(1.0f))

        Text(
            modifier = Modifier
                .padding(bottom = 32.dp),
            text = MultiplatformResource.strings.authorization.localize(),
            style = B.typography().main.mediumText,
            color = B.colors().white,
        )

        InputTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = login,
            singleLine = true,
            onTextChange = { value ->
                onSetLogin(value)
            },
            hintText = MultiplatformResource.strings.login.localize(),
            errorText = loginError,
            isError = !loginError.isNullOrBlank(),
            maxLength = Constants.Numbers.maxLenghtField,
        )

        InputTextField(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            text = password,
            singleLine = true,
            onTextChange = { value ->
                onSetPassword(value)
            },
            hintText = MultiplatformResource.strings.password.localize(),
            errorText = passwordError,
            isError = !passwordError.isNullOrBlank(),
            maxLength = Constants.Numbers.maxLenghtField,
            keyboardType = KeyboardType.Password
        )

        MainButton(
            modifier = Modifier.padding(horizontal = 24.dp),
            text = MultiplatformResource.strings.enter.localize(),
            contentColor = B.colors().black,
            backgroundColor = B.colors().white,
            cornerShapeSize = 100.dp,
            onClick = onLogin
        )

        Spacer(modifier = Modifier.weight(1.0f))

        TextButton(
            text = MultiplatformResource.strings.supportService.localize(),
            onClick = onCallService,
            textStyle = B.typography().text.buttonText,
            normalTextColor = B.colors().black.copy(alpha = 0.6f)
        )
    }
}

@Composable
@Preview
internal fun AuthorizationScreenContent_Preview() {
    globalApplicationContext = LocalContext.current
    MainTheme {
        AuthorizationScreenContent(
            login = "",
            loginError = "",
            password = "",
            passwordError = "",
            onSetLogin = {

            },
            onSetPassword = {

            },
            onLogin = {

            },
            onCallService = {

            },
            onSetScreen = {

            }
        )
    }
}