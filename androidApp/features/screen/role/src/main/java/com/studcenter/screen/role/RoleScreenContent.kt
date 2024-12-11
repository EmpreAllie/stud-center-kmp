package com.studcenter.screen.role

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.MainButton

@Composable
fun RoleScreenContent(
    onSetScreen: (Screen?) -> Unit
) {
    val words = MultiplatformResource.strings.studCenter.localize().split(" ")

    val styledText = buildAnnotatedString {
        words.forEachIndexed { index, word ->
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(word.first().uppercase())
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                append(word.substring(1))
            }
            if (index < words.size - 1) append("\n")
        }
    }

    Column(
        modifier = Modifier
            .background(B.colors().primary)
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(
            modifier = Modifier
                .padding(bottom = 32.dp),
            text = styledText,
            style = B.typography().role.title,
            color = B.colors().white
        )

        Column(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxSize()
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.3f))

            MainButton(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, bottom = 24.dp),
                text = MultiplatformResource.strings.student.localize(),
                textStyle = B.typography().role.button,
                backgroundColor = B.colors().white,
                cornerShapeSize = 12.dp,
                contentColor = B.colors().secondary,
                contentPadding = PaddingValues(vertical = 24.dp),
            ) {
                onSetScreen(Screen.RECORD)
            }

            MainButton(
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, bottom = 16.dp),
                text = MultiplatformResource.strings.employee.localize(),
                textStyle = B.typography().role.button,
                backgroundColor = B.colors().white,
                cornerShapeSize = 12.dp,
                contentColor = B.colors().secondary,
                contentPadding = PaddingValues(vertical = 24.dp),
            ) {
                onSetScreen(Screen.AUTHORIZATION)
            }

            Spacer(modifier = Modifier.weight(1.0f))

        }
    }
}

@Composable
@Preview
internal fun RoleScreenContent_Preview() {
    globalApplicationContext = LocalContext.current

    MainTheme {
        RoleScreenContent(
            onSetScreen = {

            }
        )
    }
}