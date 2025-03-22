package com.studcenter.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.R as A

@Composable
fun SplashScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(B.colors().white)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .scale(1.5f),
            painter = painterResource(id = A.drawable.logo_app),
            contentDescription = "StudyCenter",
        )
    }
}

@Composable
@Preview
internal fun SplashScreenContent_Preview() {
    MainTheme {
        SplashScreenContent()
    }
}