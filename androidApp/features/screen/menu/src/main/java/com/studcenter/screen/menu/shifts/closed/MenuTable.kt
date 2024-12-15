package com.studcenter.screen.menu.shifts.closed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme

@Composable
fun MenuTable(
    tableItem: TableItem,
    onClick: (Int) -> Unit = {},
) {
    val employeeFormatted = tableItem.employeeFormatted
    val isAvailable = employeeFormatted.isNullOrBlank()

    val contentColor: Color

    val backgroundColor = if (isAvailable) {
        contentColor = B.colors().black
        B.colors().freeTable
    } else {
        contentColor = B.colors().white
        B.colors().fullTable
    }

    val employeeText = if (isAvailable) {
        MultiplatformResource.strings.tableFree.localize()
    } else {
        employeeFormatted ?: MultiplatformResource.strings.yourPosition.localize()
    }

    Column(
        modifier = Modifier
            .clickable {
                if (isAvailable) {
                    onClick(tableItem.id)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 12.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                text = "${tableItem.numberElement}",
                color = contentColor,
                style = B.typography().menu.title
                )
        }

        Text(
            modifier = Modifier,
            text = employeeText,
            color = B.colors().white,
            style = B.typography().menu.tableDescription,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
internal fun MenuTable_Preview() {
    globalApplicationContext = LocalContext.current

    MainTheme {
        Column(modifier = Modifier) {
            MenuTable(
                tableItem = TableItem(
                    id = 0,
                    numberElement = 1,
                    employeeFormatted = "Стол свободен"
                )
            )
        }
    }
}