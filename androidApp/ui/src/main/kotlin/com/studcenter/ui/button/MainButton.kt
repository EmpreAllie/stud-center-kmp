package com.studcenter.ui.button

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.studcenter.ui.theme.B
import com.studcenter.ui.theme.MainTheme
import com.studcenter.ui.theme.invert

/**
 * Главная кнопка
 */
@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = B.typography.common.buttonText,
    contentColor: Color = B.colors.white,
    backgroundColor: Color = B.colors.primary,
    disabledColor: Color = B.colors.gray,
    disabledContentColor: Color = contentColor.invert(),
    isLoading: Boolean = false,
    borderRadius: Dp = 1.dp,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (isEnabled) backgroundColor else disabledColor)
            .fillMaxWidth()
            .padding(vertical = 16.dp)
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
                trackColor = B.colors.transparent,
                color = if (isEnabled) B.colors.white else B.colors.secondary,
                strokeWidth = 3.dp,
                strokeCap = StrokeCap.Square,
            )
        } else {
            Text(
                text = text,
                style = textStyle,
                color = if (isEnabled) contentColor else disabledContentColor,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
        }
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun MainButtonDark_Preview() {
    MainTheme {
        Column(
            modifier = Modifier
                .background(B.colors.white)
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "First Button",
                onClick = {}
            )

            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "First Button",
                isLoading = true,
                onClick = {}
            )

            MainButton(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = "First Button",
                isLoading = true,
                isEnabled = false,
                onClick = {}
            )
        }
    }
}