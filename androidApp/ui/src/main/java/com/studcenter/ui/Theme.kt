package com.studcenter.ui

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.studcenter.resources.MultiplatformResource

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        Colors.Dark(LocalContext.current)
    } else {
        Colors.Light(LocalContext.current)
    }

    CompositionLocalProvider(
        localColors(LocalContext.current) provides colors,
        localTypography() provides Typography(regular = regular(), bold = bold())
    ) {
        MaterialTheme(
            content = content,
        )
    }
}

fun regular() = FontFamily( Font(resId = MultiplatformResource.fonts.inter.fontResourceId) )
fun bold() = FontFamily( Font(resId = MultiplatformResource.fonts.inter_bold.fontResourceId) )
fun localColors(context: Context) = compositionLocalOf <Colors> { Colors.Light(context) }
fun localTypography() = compositionLocalOf { Typography(regular = regular(), bold = bold()) }

object B {
    @Composable
    fun colors(): Colors {
        return localColors(context = LocalContext.current).current
    }

    @Composable
    fun typography(): Typography {
        return localTypography().current
    }
}