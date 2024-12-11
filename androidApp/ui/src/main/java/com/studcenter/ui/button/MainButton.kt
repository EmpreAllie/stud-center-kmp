package com.studcenter.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.geometry.CornerRadius
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

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String,
    isFillWidth: Boolean = true,
    contentColor: Color = B.colors().white,
    contentPadding: PaddingValues = if (isFillWidth) PaddingValues() else PaddingValues(16.dp),
    textStyle: TextStyle = B.typography().text.buttonText,
    backgroundColor: Color = B.colors().secondary,
    cornerShapeSize: Dp = 5.dp,
    disabledColor: Color = B.colors().secondary.invert(),
    disabledContentColor: Color = contentColor.invert(),
    isLoading: Boolean = false,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerShapeSize))
            .background(if (isEnabled) backgroundColor else disabledColor)
            .then(if (isFillWidth) Modifier.fillMaxWidth() else Modifier)
            .height(56.dp)
            .clickable {
                if (isEnabled && !isLoading) {
                    onClick()
                }
            },
        contentAlignment = Alignment.Center,
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
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
internal fun MainButton_Preview() {
    MainTheme {
        Column(modifier = Modifier
            .background(B.colors().primary)
            .padding(32.dp)
        ) {
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "First Button", onClick = {})
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .shadow(elevation = 5.dp),
                text = "Создать аккаунт", backgroundColor = B.colors().white, contentColor = B.colors().secondary, textStyle = B.typography().text.buttonText) {
                
            }
        }
    }
}