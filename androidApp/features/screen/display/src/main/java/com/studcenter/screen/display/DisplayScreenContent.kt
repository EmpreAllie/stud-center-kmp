package com.studcenter.screen.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.ImageButton

@Composable
fun DisplayScreenContent(
    positionFormatted: String,
    statusFormatted: String,
    onSetScreen: (Screen?) -> Unit,
) {
    val statusText = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(MultiplatformResource.strings.status.localize() + ": ")
        }
        withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
            append(statusFormatted.removePrefix(MultiplatformResource.strings.status.localize()))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(B.colors().primary)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ImageButton(
                modifierIcon = Modifier,
                iconId = MultiplatformResource.images.ic_back.drawableResId,
                iconColor = B.colors().white
            ) {
                onSetScreen(null)
            }
        }

        Spacer(modifier = Modifier.weight(1.0f))

        Text(
            modifier = Modifier
                .padding(bottom = 32.dp),
            text = positionFormatted,
            style = B.typography().main.bigText,
            color = B.colors().white,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier,
            text = statusText,
            color = B.colors().white,
            textAlign = TextAlign.Justify,
            style = B.typography().main.mediumText,
        )

        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Composable
@Preview
internal fun DisplayScreenContent_Preview() {
    globalApplicationContext = LocalContext.current
    MainTheme {
        DisplayScreenContent(
            positionFormatted = "Ваша позиция\n12",
            statusFormatted = "Ожидание",
            onSetScreen = {

            }
        )
    }
}