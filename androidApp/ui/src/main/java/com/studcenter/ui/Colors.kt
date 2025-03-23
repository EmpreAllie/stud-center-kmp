package com.studcenter.ui

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.studcenter.resources.MultiplatformResource

/**
 * Colors - цветовая палитра приложения (для светлой и темной темы)
 */
sealed class Colors {
    abstract val primary: Color
    abstract val secondary: Color
    abstract val error: Color
    abstract val green: Color
    abstract val freeTable: Color
    abstract val pendingTable: Color
    abstract val fullTable: Color
    abstract val white: Color
    abstract val black: Color
    abstract val gray: Color
    abstract val transparent: Color

    data class Light(
        private val context: Context,
        override val primary: Color = Color(MultiplatformResource.colors.primaryColor.getColor(context)),
        override val secondary: Color = Color(MultiplatformResource.colors.secondaryColor.getColor(context)),
        override val white: Color = Color(MultiplatformResource.colors.white.getColor(context)),
        override val black: Color = Color(MultiplatformResource.colors.black.getColor(context)),
        override val error: Color = Color(MultiplatformResource.colors.errorColor.getColor(context)),
        override val transparent: Color = Color.Transparent,
        override val green: Color = Color(MultiplatformResource.colors.green.getColor(context)),
        override val gray: Color = Color(MultiplatformResource.colors.gray.getColor(context)),
        override val freeTable: Color = Color(MultiplatformResource.colors.freeTable.getColor(context)),
        override val pendingTable: Color = Color(MultiplatformResource.colors.pendingTable.getColor(context)),
        override val fullTable: Color = Color(MultiplatformResource.colors.fullTable.getColor(context)),
    ): Colors()

    data class Dark(
        private val context: Context,
        override val primary: Color = Color(MultiplatformResource.colors.primaryColor.getColor(context)),
        override val secondary: Color = Color(MultiplatformResource.colors.secondaryColor.getColor(context)),
        override val white: Color = Color(MultiplatformResource.colors.white.getColor(context)),
        override val black: Color = Color(MultiplatformResource.colors.black.getColor(context)),
        override val error: Color = Color(MultiplatformResource.colors.errorColor.getColor(context)),
        override val transparent: Color = Color.Transparent,
        override val green: Color = Color(MultiplatformResource.colors.green.getColor(context)),
        override val gray: Color = Color(MultiplatformResource.colors.gray.getColor(context)),
        override val freeTable: Color = Color(MultiplatformResource.colors.freeTable.getColor(context)),
        override val pendingTable: Color = Color(MultiplatformResource.colors.pendingTable.getColor(context)),
        override val fullTable: Color = Color(MultiplatformResource.colors.fullTable.getColor(context)),
    ): Colors()
}

fun Color.invert(): Color {
    return Color(1f - red, 1f - green, 1f - blue, alpha)
}