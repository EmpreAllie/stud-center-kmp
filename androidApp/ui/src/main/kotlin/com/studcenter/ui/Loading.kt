package com.studcenter.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.ui.theme.B
import com.studcenter.ui.theme.MainTheme


@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = B.colors.transparent
) {
    Box(
        modifier = modifier
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(22.dp),
            color = B.colors.primary,
            trackColor = B.colors.transparent,
            strokeWidth = 2.dp,
            strokeCap = StrokeCap.Square
        )
    }
}

@Preview(name = "Light Mode")
@Composable
fun LoadingContentLight_Preview() {
    MainTheme {
        LoadingContent()
    }
}

@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoadingContentDark_Preview() {
    MainTheme {
        LoadingContent()
    }
}