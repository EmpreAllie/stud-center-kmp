package com.studcenter.screen.menu.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.studcenter.data.utils.globalApplicationContext
import com.studcenter.data.utils.localize
import com.studcenter.domain.constants.Constants
import com.studcenter.resources.MultiplatformResource
import com.studcenter.ui.B
import com.studcenter.ui.MainTheme
import com.studcenter.ui.button.MainButton
import com.studcenter.ui.button.TextButton

@Composable
fun DrawerContent(
    isWork: Boolean,
    onCallService: () -> Unit,
    onCloseShifts: () -> Unit,
    onExit: () -> Unit,

) {
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .size(280.dp)
        .background(B.colors().white)
    ) {
        item {

            Text(
                modifier = Modifier
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                text = MultiplatformResource.strings.navigationMenu.localize(),
                style = B.typography().navigation.title,
                color = B.colors().black
            )
            
            HorizontalDivider()

            if (isWork) {
                MainButton(
                    text = MultiplatformResource.strings.closeShifts.localize(),
                    backgroundColor = B.colors().transparent,
                    contentColor = B.colors().black,
                    cornerShapeSize = 0.dp,
                    onClick = onCloseShifts,
                    textStyle = B.typography().navigation.button,
                    contentAlignment = Alignment.CenterStart,
                    contentPadding = PaddingValues(16.dp)
                )

                HorizontalDivider()
            }

            MainButton(
                text = MultiplatformResource.strings.callService.localize(),
                backgroundColor = B.colors().transparent,
                contentColor = B.colors().black,
                cornerShapeSize = 0.dp,
                textStyle = B.typography().navigation.button,
                contentAlignment = Alignment.CenterStart,
                textAlign = TextAlign.Start,
                contentPadding = PaddingValues(16.dp),
                onClick = onCallService
            )

            HorizontalDivider()

            MainButton(
                text = MultiplatformResource.strings.exitAccount.localize(),
                backgroundColor = B.colors().transparent,
                contentColor = B.colors().black,
                cornerShapeSize = 0.dp,
                textStyle = B.typography().navigation.button,
                contentAlignment = Alignment.CenterStart,
                textAlign = TextAlign.Start,
                contentPadding = PaddingValues(16.dp),
                onClick = onExit
            )
            HorizontalDivider()


            /*MainButton(
                modifier = Modifier.padding(bottom = 16.dp),
                text = MultiplatformResource.strings.profile.localize(),
                textStyle = B.typography().drawer.button,
            ) {
                navigationParams.onOpenProfile()
            }*/
        }
    }
}

@Composable
@Preview
internal fun DrawerContent_Preview() {
    globalApplicationContext = LocalContext.current // TODO: Remove
    MainTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(B.colors().white)
            .background(B.colors().black.copy(alpha = 0.8f))
        ) {
            DrawerContent(
                isWork = true,
                onCallService = {

                },
                onCloseShifts = {

                },
                onExit = {

                }
            )
        }
    }
}
