package com.studcenter.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Поле для ввода
 * @param modifier Настройки поля для ввода
 * @param text Отображаемый текст
 * @param hintText Скрытый текст
 * @param errorText Текст ошибки
 * @param isError Отобразить ошибку
 * @param isEnabled Работает ли текстовое поле
 * @param isSingleLine Однострочный текст
 * @param height Высота текстового поля
 * @param textSelection Выделение текста (в основном не нужно)
 * @param keyboardType Тип клавиатуры (отобразить только цифры, все символы и т.д.)
 * @param maxLength Максимальное количество символов
 * @param maxLines Максимальное количество строк
 * @param trailingIcon Иконка справа от текстового поля
 * @param onTextChange Действие при нажатии
 * @sample InputTextFieldDark_Preview
 */
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hintText: String,
    errorText: String? = null,
    isError: Boolean = !errorText.isNullOrBlank(),
    isEnabled: Boolean = true,
    isSingleLine: Boolean = false,
    height: Dp = 70.dp,
    textSelection: Int = 0,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    trailingIcon: (@Composable () -> Unit)? = null,
    onTextChange: (String) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    val isFocused: MutableState<Boolean> = remember { mutableStateOf(false) }

    val borderColor = if (isError || !errorText.isNullOrEmpty()) {
        B.colors().error
    } else if (isFocused.value) {
        B.colors().primary
    } else {
        B.colors().primary.copy(alpha = 0.6f)
    }

    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .background(B.colors().white)
            .heightIn(min = height)
        ,
        contentAlignment = Alignment.CenterStart,
    ) {

        val customTextSelectionColors = TextSelectionColors(
            handleColor = B.colors().secondary,
            backgroundColor = B.colors().black.copy(0.2f)
        )

        var textFieldValue by remember {
            mutableStateOf(
                TextFieldValue(
                    text = text,
                    selection = TextRange(textSelection)
                )
            )
        }

        LaunchedEffect(text) {
            textFieldValue = textFieldValue.copy(text = text, selection = TextRange(text.lastIndex + 1))
        }

        if (textFieldValue.text.isEmpty()) {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
                text = hintText,
                style = B.typography().text.hintText,
                color = (if (isError || !errorText.isNullOrEmpty()) B.colors().error else B.colors().black).copy(alpha = 0.4f),
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                    BasicTextField(
                        modifier = Modifier
                            .weight(1.0f)
                            .padding(
                                top = 0.dp,
                                start = 0.dp,
                                bottom = 0.dp,
                                end = if (trailingIcon != null) 16.dp else 8.dp
                            )
                            .onFocusChanged {
                                if (!isEnabled) {
                                    focusManager.clearFocus()
                                }
                                isFocused.value = it.isFocused
                            },
                        enabled = isEnabled,
                        value = textFieldValue,
                        maxLines = maxLines,
                        singleLine = isSingleLine,
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType,),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        },
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                                focusManager.clearFocus()
                            }),
                        textStyle = B.typography().text.inputText.copy(color = B.colors().secondary),
                        onValueChange = { newValue ->
                            if (isEnabled) {
                                if (newValue.text.length > maxLength) {
                                    return@BasicTextField
                                }

                                textFieldValue = newValue
                                onTextChange(textFieldValue.text)
                            }
                        },
                        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
                    )
                }

            trailingIcon?.invoke()
        }
    }

    if (errorText != null) {
        Text(
            modifier = Modifier.padding(
                top = 0.dp,
                start = 20.dp,
                end = 20.dp,
            ),
            text = errorText,
            overflow = TextOverflow.Ellipsis,
            color = B.colors().error,
            style = B.typography().text.inputText,
            textAlign = TextAlign.Start
        )
    }
}


@Preview(name = "Light Mode")
@Composable
internal fun InputTextFieldLight_Preview() {
    MainTheme {
        Column(modifier = Modifier
            .background(B.colors().primary)) {
            InputTextField(
                modifier = Modifier.
                padding(bottom = 16.dp),
                hintText = "Пароль",
                isSingleLine = true,
                errorText = "",
                isError = false,
                isEnabled = true
            )
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun InputTextFieldDark_Preview() {
    MainTheme {
        Column(modifier = Modifier
            .background(B.colors().primary)) {
            InputTextField(
                modifier = Modifier.
                padding(bottom = 16.dp),
                hintText = "Пароль",
                isSingleLine = true,
                errorText = "",
                isError = false,
                isEnabled = true,
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
            )
        }
    }
}