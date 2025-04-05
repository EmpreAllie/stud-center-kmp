package com.studcenter.ui.theme


import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(private val font: FontFamily) {
    val common: TitleTypography = TitleTypography(font)
    val menu: MenuTypography = MenuTypography(font)
}

@Immutable
data class TitleTypography(private val font: FontFamily) {
    val title: TextStyle = TextStyle(
        fontSize = 32.sp,
        lineHeight = 28.sp,
        fontFamily = font,
        fontWeight = FontWeight.W600
    ).preciseLineHeight()

    val main: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontFamily = font,
        fontWeight = FontWeight.W500
    ).preciseLineHeight()

    val buttonText: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 26.sp,
        fontFamily = font,
        fontWeight = FontWeight.W600,
    )

    val inputText: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 25.sp,
        fontFamily = font,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val hintText: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 28.sp,
        fontFamily = font,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()
}

@Immutable
data class MenuTypography(private val font: FontFamily) {
    val title: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = font,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val tableDescription: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = font,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val itemKey: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = font,
        fontWeight = FontWeight.W600
    ).preciseLineHeight()

    val itemValue: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = font,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()
}

internal fun TextStyle.preciseLineHeight(): TextStyle = this.copy(
    platformStyle = PlatformTextStyle(false),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)