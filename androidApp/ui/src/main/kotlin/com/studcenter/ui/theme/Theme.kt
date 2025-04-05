package com.studcenter.ui.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.studcenter.resources.MultiplatformResource

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors =
        if (darkTheme) Colors.Dark(LocalContext.current) else Colors.Light(LocalContext.current)

    CompositionLocalProvider(
        localColors(LocalContext.current) provides colors,
        localTypography provides Typography(font)
    ) {
        MaterialTheme(
            content = content
        )
    }
}

private val font = FontFamily(
    Font(MultiplatformResource.fonts.inter.fontResourceId, FontWeight.W400),
    Font(MultiplatformResource.fonts.inter_bold.fontResourceId, FontWeight.W600)
)
private val localTypography = compositionLocalOf { Typography(font) }
fun localColors(context: Context) = compositionLocalOf<Colors> { Colors.Light(context) }

object B {
    val typography: Typography
        @Composable
        get() = localTypography.current

    val colors: Colors
        @Composable
        get() = localColors(context = LocalContext.current).current
}