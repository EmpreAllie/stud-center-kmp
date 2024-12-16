package com.studcenter.ui


import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp

@Immutable
data class Typography(private val regular: FontFamily, private val bold: FontFamily) {
    val text: TitleTypography = TitleTypography(regular, bold)
    val main: MainTypography = MainTypography(regular, bold)
    val menu: MenuTypography = MenuTypography(regular, bold)
    val navigation: NavigationTypography = NavigationTypography(regular, bold)
}

@Immutable
data class TitleTypography(
    private val regular: FontFamily,
    private val bold: FontFamily
) {
    val title: TextStyle = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val main: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 28.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val buttonText: TextStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400,
    )

    val inputText: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 28.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val hintText: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 28.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()
}

@Immutable
data class MainTypography(
    private val regular: FontFamily,
    private val bold: FontFamily
) {
    val bigText: TextStyle = TextStyle(
        fontSize = 40.sp,
        lineHeight = 50.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val mediumText: TextStyle = TextStyle(
        fontSize = 32.sp,
        lineHeight = 26.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val text: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val button: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()
}


@Immutable
data class MenuTypography(
    private val regular: FontFamily,
    private val bold: FontFamily
) {
    val title: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val tableDescription: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val itemKey: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val itemValue: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 22.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()
}

@Immutable
data class NavigationTypography(
    private val regular: FontFamily,
    private val bold: FontFamily
) {
    val title: TextStyle = TextStyle(
        fontSize = 28.sp,
        lineHeight = 22.sp,
        fontFamily = bold,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()

    val button: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontFamily = regular,
        fontWeight = FontWeight.W400
    ).preciseLineHeight()
}

fun TextStyle.preciseLineHeight(): TextStyle = this.copy(
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)