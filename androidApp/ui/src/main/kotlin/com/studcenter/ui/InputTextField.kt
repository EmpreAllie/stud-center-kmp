package com.studcenter.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.theme.B
import com.studcenter.ui.theme.MainTheme

/**
 * TODO: Написать java doc
 */
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    placeholder: String,
    errorText: String? = null,
    isSingleLine: Boolean = false,
    isEnabled: Boolean = true,
    maxLength: Int = Int.MAX_VALUE,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = B.typography.common.inputText,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardDone: () -> Unit = {},
    onKeyboardNext: () -> Unit = {},
    supportingText: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        enabled = isEnabled,
        singleLine = isSingleLine,
        isError = !errorText.isNullOrEmpty(),
        maxLines = maxLines,
        textStyle = textStyle,
        shape = RoundedCornerShape(8.dp),
        supportingText = {
            if (!errorText.isNullOrEmpty()) {
                AnimatedVisibility(errorText.isNotBlank()) {
                    Text(
                        text = errorText.orEmpty(),
                        style = B.typography.common.hintText,
                        color = B.colors.error
                    )
                }
            } else {
                supportingText?.invoke()
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                style = textStyle
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (!errorText.isNullOrEmpty()) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = MultiplatformResource.images.ic_error.drawableResId),
                    contentDescription = null
                )
            } else {
                trailingIcon?.invoke()
            }
        },
        onValueChange = {
            if (it.length > maxLength) return@OutlinedTextField
            onTextChange(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = B.colors.primary,
            unfocusedTextColor = B.colors.secondary,
            errorTextColor = B.colors.error,
            cursorColor = B.colors.primary,
            errorCursorColor = B.colors.primary,
            focusedBorderColor = B.colors.primary,
            unfocusedBorderColor = B.colors.primary.copy(alpha = 0.6f),
            errorBorderColor = B.colors.error,
            focusedLeadingIconColor = B.colors.primary,
            unfocusedLeadingIconColor = B.colors.primary.copy(alpha = 0.6f),
            errorLeadingIconColor = B.colors.primary,
            focusedTrailingIconColor = B.colors.secondary,
            unfocusedTrailingIconColor = B.colors.secondary,
            errorTrailingIconColor = B.colors.error,
            focusedPlaceholderColor = B.colors.primary,
            unfocusedPlaceholderColor = B.colors.primary.copy(alpha = 0.6f),
            errorPlaceholderColor = B.colors.primary,
            focusedSupportingTextColor = B.colors.primary,
            unfocusedSupportingTextColor = B.colors.primary,
            errorSupportingTextColor = B.colors.error,
            selectionColors = TextSelectionColors(
                handleColor = B.colors.primary,
                backgroundColor = B.colors.primary.copy(.3f)
            )
        ),
        keyboardActions = KeyboardActions(
            onNext = { onKeyboardNext() },
            onDone = { onKeyboardDone() }
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}


@Preview(name = "Light Mode")
@Composable
internal fun InputTextFieldLight_Preview() {
    MainTheme {
        Column(
            modifier = Modifier
                .background(B.colors.white)
                .padding(24.dp)
        ) {
            InputTextField(
                modifier = Modifier,
                text = "123",
                placeholder = "Пароль",
                isSingleLine = true,
                errorText = "",
                isEnabled = true
            )
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun InputTextFieldDark_Preview() {
    MainTheme {
        Column(
            modifier = Modifier
                .background(B.colors.white)
                .padding(24.dp)
        ) {
            InputTextField(
                modifier = Modifier,
                isSingleLine = true,
                text = "123",
                errorText = "123",
                isEnabled = true,
                placeholder = "Пароль"
            )
        }
    }
}