package com.studcenter.ui.button

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.invert

/**
 * Главная кнопка
 * @param modifier Настройки кнопки
 * @param isLoading Поместить эффект загрузки вместо контента
 * @param isEnabled Активирована ли кнопка
 * @param text Текст
 * @param textAlign Выравнивание текста
 * @param textStyle Стиль текста
 * @param backgroundColor Фон кнопки
 * @param contentColor Цвет текста
 * @param contentPadding Отступы у текста
 * @param contentAlignment Выравнивание текста относительно кнопки
 * @param disabledColor Цвет кнопки, если кнопка не активирована (isEnabled == false)
 * @param disabledContentColor Цвет текста, если кнопка не активирована (isEnabled == false)
 * @param cornerShapeSize Радиус у кнопки
 * @param onClick Действие при нажатии
 * @sample MainButtonLight_Preview
 */
@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    textStyle: TextStyle = B.typography().text.buttonText,
    backgroundColor: Color = B.colors().secondary,
    contentColor: Color = B.colors().white,
    contentPadding: PaddingValues = PaddingValues(vertical = 16.dp),
    contentAlignment: Alignment = Alignment.Center,
    disabledColor: Color = B.colors().secondary.invert(),
    disabledContentColor: Color = contentColor.invert(),
    cornerShapeSize: Dp = 15.dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerShapeSize))
            .background(if (isEnabled) backgroundColor else disabledColor)
            .clickable {
                if (isEnabled && !isLoading) {
                    onClick()
                }
            },
        contentAlignment = contentAlignment,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(22.dp),
                color = if (isEnabled) B.colors().white else B.colors().secondary,
                trackColor = B.colors().transparent,
                strokeWidth = 2.dp,
                strokeCap = StrokeCap.Square
            )
        }

        Text(
            modifier = Modifier
                .padding(contentPadding)
                .alpha(if (isLoading) 0.0f else 1.0f),
            text = text,
            style = textStyle,
            color = if (isEnabled) contentColor else disabledContentColor,
            textAlign = textAlign,
        )
    }
}

@Preview(name = "Light Mode")
@Composable
internal fun MainButtonLight_Preview() {
    MainTheme {
        Column(modifier = Modifier
            .background(B.colors().primary)
            .padding(32.dp)
        ) {
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "First Button"
            ) {}
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .shadow(elevation = 5.dp),
                contentColor = B.colors().secondary,
                text = "Создать аккаунт",
                textStyle = B.typography().text.buttonText,
                backgroundColor = B.colors().white
            ) {
                
            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun MainButtonDark_Preview() {
    MainTheme {
        Column(modifier = Modifier
            .background(B.colors().primary)
            .padding(32.dp)
        ) {
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "First Button"
            ) {}
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .shadow(elevation = 5.dp),
                contentColor = B.colors().secondary,
                text = "Создать аккаунт",
                textStyle = B.typography().text.buttonText,
                backgroundColor = B.colors().white
            ) {

            }
        }
    }
}