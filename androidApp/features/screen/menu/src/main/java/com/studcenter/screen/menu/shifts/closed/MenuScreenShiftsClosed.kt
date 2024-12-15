package com.studcenter.screen.menu.shifts.closed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme

@Composable
fun MenuScreenShiftsClosed(
    modifier: Modifier = Modifier,
    tableItems: List<TableItem>,
    onClickTable: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tableItems.size) { index ->
            val item = tableItems[index]
            MenuTable(
                tableItem = item,
                onClick = onClickTable
            )
        }
    }
}

@Composable
@Preview
internal fun MenuScreenShiftsClosed_Preview() {
    globalApplicationContext = LocalContext.current

    MainTheme {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(B.colors().primary)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            MenuScreenShiftsClosed(
                tableItems = listOf(
                    TableItem(
                        id = 0,
                        numberElement = 1,
                        employeeFormatted = "Иванов И. И."
                    ),
                    TableItem(
                        id = 0,
                        numberElement = 2,
                        employeeFormatted = "Смирнов С. С."
                    ),
                    TableItem(
                        id = 0,
                        numberElement = 3,
                        employeeFormatted = null
                    )
                ),
                onClickTable = {}
            )
        }
    }
}