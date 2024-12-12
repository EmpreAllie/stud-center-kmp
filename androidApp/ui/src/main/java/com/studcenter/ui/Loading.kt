package com.studcenter.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoadingContent() {
    CircularProgressIndicator(
        modifier = Modifier.size(22.dp),
        color = B.colors().primary,
        trackColor = B.colors().transparent,
        strokeWidth = 2.dp,
        strokeCap = StrokeCap.Square
    )
}

@Composable
fun LoadingContentFull() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(B.colors().black.copy(alpha = 0.8f)),
        contentAlignment = Alignment.Center
    ) {
        LoadingContent()
    }
}

@Composable
@Preview
internal fun LoadingContentFull_Preview() {
    MainTheme {
        LoadingContentFull()
    }
}