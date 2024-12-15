package com.studcenter.screen.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.features.menu.domain.model.TableItem
import com.studcenter.features.menu.domain.model.WorkInfo
import com.studcenter.resources.MultiplatformResource
import com.studcenter.screen.menu.shifts.closed.MenuScreenShiftsClosed
import com.studcenter.screen.menu.shifts.open.MenuScreenShiftsWork
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.ImageButton

@Composable
fun MenuScreenContent(
    isWork: Boolean,
    workInfo: WorkInfo?,
    tableItems: List<TableItem>,
    onClickTable: (Int) -> Unit,
    tableFormatted: String,
    onOpenMenu: () -> Unit,
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(B.colors().white),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ImageButton(
                modifierIcon = Modifier,
                iconId = MultiplatformResource.images.ic_menu.drawableResId,
                iconColor = B.colors().black,
                onClick = onOpenMenu
            )

            Spacer(modifier = Modifier.weight(1.0f))

            Text(
                text = tableFormatted,
                color = B.colors().black,
                style = B.typography().menu.title,
            )

            Spacer(modifier = Modifier.weight(1.0f))
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(B.colors().primary)
                .padding(top = 16.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isWork && workInfo != null) {
                MenuScreenShiftsWork(
                    currentNumber = workInfo.currentNumber,
                    firstName = workInfo.firstName,
                    lastName = workInfo.lastName,
                    middleName = workInfo.middleName,
                    group = workInfo.group,
                    inQueueNumber = workInfo.inQueueNumber,
                    onNext = onNext
                )
            } else {
                MenuScreenShiftsClosed(
                    tableItems = tableItems,
                    onClickTable = onClickTable
                )
            }
        }
    }
}

@Composable
@Preview
internal fun MenuScreenContent_Preview() {
    globalApplicationContext = LocalContext.current
    MainTheme {
        MenuScreenContent(
            isWork = true,
            workInfo = WorkInfo(
                currentNumber = 12,
                firstName = "1",
                lastName = "",
                middleName = "123",
                group = "123",
                inQueueNumber = 0
            ),
            tableFormatted = "Cтол не выбран",
            tableItems = listOf(
                TableItem(
                    id = 2,
                    numberElement = 1,
                    employeeFormatted = "Фамилия И. О."
                ),
                TableItem(
                    id = 2,
                    numberElement = 1,
                    employeeFormatted = null
                ),
                TableItem(
                    id = 2,
                    numberElement = 1,
                    employeeFormatted = "Фамилия И. О."
                )
            ),
            onClickTable = {

            },
            onOpenMenu = {

            },
            onNext = {

            }
        )
    }
}