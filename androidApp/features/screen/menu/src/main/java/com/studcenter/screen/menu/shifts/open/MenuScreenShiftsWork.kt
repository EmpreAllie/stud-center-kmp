package com.studcenter.screen.menu.shifts.open

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.format
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.MainButton

@Composable
fun MenuScreenShiftsWork(
    modifier: Modifier = Modifier,
    currentNumber: Int?,
    firstName: String,
    lastName: String,
    middleName: String?,
    group: String,
    inQueueNumber: Int,
    onNext: () -> Unit,
) {
    val isEnabledButton = inQueueNumber != 0

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        if (currentNumber == null) {
            Spacer(modifier = Modifier.weight(1.0f))
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(top = 32.dp, bottom = 6.dp),
                    text = MultiplatformResource.strings.currentNumber.localize() + ":",
                    style = B.typography().main.bigText,
                    color = B.colors().white
                )

                Text(
                    modifier = Modifier,
                    text = currentNumber?.toString() ?: "-",
                    style = B.typography().main.bigText,
                    color = B.colors().white,
                )

                if (currentNumber != null) {
                    Column(
                        modifier = Modifier
                            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                    ) {
                        MenuScreenItemRow(
                            modifier = Modifier
                                .padding(bottom = 8.dp),
                            key = MultiplatformResource.strings.lastName.localize(),
                            value = lastName.ifEmpty { "-" }
                        )

                        MenuScreenItemRow(
                            modifier = Modifier,
                            key = MultiplatformResource.strings.firstName.localize(),
                            value = firstName.ifEmpty { "-" }
                        )

                        if (!middleName.isNullOrEmpty()) {
                            MenuScreenItemRow(
                                modifier = Modifier
                                    .padding(top = 8.dp),
                                key = MultiplatformResource.strings.middleName.localize(),
                                value = middleName
                            )
                        }

                        MenuScreenItemRow(
                            modifier = Modifier
                                .padding(top = 24.dp),
                            key = MultiplatformResource.strings.group.localize(),
                            value = group.ifEmpty { "-" }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Text(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = MultiplatformResource.strings.inQueue.format("$inQueueNumber"),
            color = B.colors().white,
            style = B.typography().main.text,
        )

        MainButton(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
            text = MultiplatformResource.strings.next_v2.localize(),
            isEnabled = isEnabledButton,
            contentColor = B.colors().black,
            disabledContentColor = B.colors().black,
            backgroundColor = B.colors().white,
            disabledColor = B.colors().gray,
            onClick = {
                onNext()
            }
        )
    }
}

@Composable
@Preview
internal fun MenuScreenShiftsWork_Preview() {
    globalApplicationContext = LocalContext.current

    MainTheme {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(B.colors().primary)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            MenuScreenShiftsWork(
                currentNumber = 123,
                firstName = "Иванов",
                lastName = "Иван",
                middleName = "Иванович",
                group = "О123Б",
                inQueueNumber = 5,
                onNext = {

                }
            )
        }
    }
}