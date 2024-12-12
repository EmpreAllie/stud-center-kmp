package com.studcenter.screen.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.studcenter.base.features.enum.Screen
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.InputTextField
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.ImageButton
import com.studcenter.ui.button.MainButton

@Composable
fun RecordScreenContent(
    firstName: String,
    lastName: String,
    middleName: String,
    group: String,
    onAccept: () -> Unit,
    onSetScreen: (Screen?) -> Unit,
) {
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


        Text(
            modifier = Modifier
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
            text = MultiplatformResource.strings.onlineRecord.localize(),
            style = B.typography().record.title,
            color = B.colors().white,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier
                .weight(1.0f)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            item {
                InputTextField(
                    modifier = Modifier,
                    hintText = MultiplatformResource.strings.lastName_Required.localize(),
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                    maxLines = 2,
                    text = lastName,
                )

                InputTextField(
                    modifier = Modifier,
                    hintText = MultiplatformResource.strings.firstName_Required.localize(),
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                    maxLines = 2,
                    text = firstName,
                )

                InputTextField(
                    modifier = Modifier,
                    hintText = MultiplatformResource.strings.middleName.localize(),
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                    maxLines = 2,
                    text = middleName,
                )

                InputTextField(
                    modifier = Modifier,
                    hintText = MultiplatformResource.strings.numberGroup_Required.localize(),
                    keyboardType = KeyboardType.Text,
                    singleLine = true,
                    maxLines = 2,
                    text = group,
                )
            }
        }

        MainButton(
            modifier = Modifier
                .padding(bottom = 8.dp),
            text = MultiplatformResource.strings.accept.localize(),
            textStyle = B.typography().record.button,
            backgroundColor = B.colors().white,
            cornerShapeSize = 100.dp,
            contentColor = B.colors().secondary,
            contentPadding = PaddingValues(vertical = 18.dp),
        ) {
            onAccept()
        }
    }
}

@Composable
@Preview
internal fun RecordScreenContent_Preview() {
    globalApplicationContext = LocalContext.current

    MainTheme {
        RecordScreenContent(
            firstName = "ss",
            lastName = "",
            middleName = "",
            group = "",
            onAccept = {},
            onSetScreen = {})
    }
}