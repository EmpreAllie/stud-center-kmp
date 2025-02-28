package com.studcenter.ui.button

import android.content.res.Configuration
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.ui.B

/**
 * Текстовая кнопка
 * @param modifier Настройки кнопки
 * @param text Текст
 * @param textStyle Стиль текста
 * @param textColor Цвет текста
 * @param onClick Действие при нажатии
 * @sample TextButtonLight_Preview
 */

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = B.colors().primary,
    textStyle: TextStyle = B.typography().text.buttonText,
    onClick: () -> Unit,
) {
    val isPressed = remember { mutableStateOf(false) }

    BasicText(
        text = text,
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed.value = true
                        tryAwaitRelease()
                        isPressed.value = false
                        onClick()
                    }
                )
            },
        style = textStyle.copy(
            color = if (isPressed.value) textColor.copy(alpha = 0.7f) else textColor
        )
    )
}


@Preview(name = "Light Mode")
@Composable
fun TextButtonLight_Preview() {
    Column() {
        TextButton(
            modifier = Modifier
                .padding(bottom = 6.dp),
            text = "Click Me",
        ) { }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TextButtonDark_Preview() {
    Column() {
        TextButton(
            modifier = Modifier
                .padding(bottom = 6.dp),
            text = "Click Me",
        ) { }
    }
}