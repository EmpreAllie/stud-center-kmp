package com.studcenter.screen.menu.shifts.open

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme

@Composable
fun MenuScreenItemRow(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    val key = "$key: "
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = key,
            style = B.typography().menu.itemKey,
            color = B.colors().white,
        )

        Text(
            text = value,
            style = B.typography().menu.itemValue,
            color = B.colors().white,
        )
    }
}

@Composable
@Preview
internal fun MenuScreenItemRow_Preview() {
    globalApplicationContext = LocalContext.current

    MainTheme {
        Column {
            MenuScreenItemRow(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                key = "key",
                value = "vavlue"
            )

            MenuScreenItemRow(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                key = "key1",
                value = "vavlue2"
            )
        }
    }
}