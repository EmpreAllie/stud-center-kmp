package com.studcenter.ui.button

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.theme.B
import com.studcenter.ui.theme.MainTheme

/**
 * Кнопка без заднего фона
 * @param modifier Настройки кнопки
 * @param isEnabled Работает ли кнопка
 * @param modifierIcon Настройки иконки
 * @param iconRes Путь к ресурсы иконки
 * @param size Размер кнопки в Dp
 * @param backgroundColor Задний фон кнопки
 * @param iconColor Цвет иконки
 * @param clipDp Радиус округления кнопки
 * @param onClick Действие при нажатии на кнопку
 * @sample ImageButton_Preview
 */
@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    modifierIcon: Modifier,
    iconRes: Int,
    size: Int = 48,
    backgroundColor: Color = B.colors.transparent,
    iconColor: Color = B.colors.secondary,
    clipDp: Dp = 1000.dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(clipDp))
            .size(size.dp)
            .background(if (isEnabled) backgroundColor else B.colors.gray)
            .clickable(enabled = isEnabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
            Icon(
                modifier = modifierIcon
                    .fillMaxSize(),
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = iconColor
            )
    }
}

/**
 * Кнопка без заднего фона
 * @param modifier Настройки кнопки
 * @param isEnabled Работает ли кнопка
 * @param iconRes Путь к ресурсы иконки
 * @param size Размер кнопки в Dp
 * @param iconColor Цвет иконки
 * @param onClick Действие при нажатии на кнопку
 * @sample ImageButtonLight_Preview
 */

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    iconRes: Int,
    size: Int = 48,
    iconColor: Color = B.colors.secondary,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size.dp)
            .border(
                width = 1.dp,
                color = B.colors.secondary,
                shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(if (isEnabled) B.colors.primary else B.colors.gray)
            .clickable(enabled = isEnabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
            ,
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = iconColor
        )
    }
}


@Preview(name = "Light Mode")
@Composable
internal fun ImageButtonLight_Preview() {
    MainTheme {
        Column {
            ImageButton(
                modifier = Modifier.padding(bottom = 16.dp),
                isEnabled = true, modifierIcon = Modifier
                    .padding(8.dp),
                iconRes = MultiplatformResource.images.ic_back.drawableResId
            ) {}

            ImageButton(iconRes = MultiplatformResource.images.ic_menu.drawableResId) {

            }
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun ImageButtonDark_Preview() {
    MainTheme {
        Column {
            ImageButton(
                modifier = Modifier.padding(bottom = 16.dp),
                isEnabled = true, modifierIcon = Modifier
                    .padding(8.dp),
                iconRes = MultiplatformResource.images.ic_back.drawableResId
            ) {}

            ImageButton(iconRes = MultiplatformResource.images.ic_menu.drawableResId) {

            }
        }
    }
}
