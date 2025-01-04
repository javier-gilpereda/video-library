package com.gilpereda.videomanager.ui.common

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class Theme(
    val colorScheme: ColorScheme,
) {
    companion object {
        val dark =
            Theme(
                colorScheme =
                    darkColorScheme(
                        background = Color(0xFF2B2B2B),
                        surface = Color(0xFF3C3F41),
                    ),
            )

        val light =
            Theme(
                colorScheme =
                    lightColorScheme(
                        background = Color(0xFFF5F5F5),
                        surface = Color(0xFFFFFFFF),
                    ),
            )
    }
}

val LocalTheme = staticCompositionLocalOf { Theme.dark }
