package com.studcenter.screen.authorization

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme

@Composable
fun AuthorizationScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(B.colors().white),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = MultiplatformResource.strings.authorization.localize(),
            style = B.typography().text.title,
            color = B.colors().primary
        )
    }
}

@Composable
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
internal fun AuthorizationScreenContent_Preview() {
    MainTheme {
        AuthorizationScreenContent()
    }
}