package com.gilpereda.videomanager.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ColorPalette(colorScheme: ColorScheme) {
    Column {
        Row(Modifier.fillMaxWidth().weight(1f)) {
            Cell(
                text = "Primary",
                background = colorScheme.primary,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Primary",
                background = colorScheme.onPrimary,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Primary Container",
                background = colorScheme.primaryContainer,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Primary Container",
                background = colorScheme.onPrimaryContainer,
                colorScheme = colorScheme,
            )
        }
        Row(Modifier.fillMaxWidth().weight(1f)) {
            Cell(
                text = "Secondary",
                background = colorScheme.secondary,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Secondary",
                background = colorScheme.onSecondary,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Secondary Container",
                background = colorScheme.secondaryContainer,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Secondary Container",
                background = colorScheme.onSecondaryContainer,
                colorScheme = colorScheme,
            )
        }
        Row(Modifier.fillMaxWidth().weight(1f)) {
            Cell(
                text = "Tertiary",
                background = colorScheme.tertiary,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Tertiary",
                background = colorScheme.onTertiary,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Tertiary Container",
                background = colorScheme.tertiaryContainer,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Tertiary Container",
                background = colorScheme.onTertiaryContainer,
                colorScheme = colorScheme,
            )
        }
        Row(Modifier.fillMaxWidth().weight(1f)) {
            Cell(
                text = "Error",
                background = colorScheme.error,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Error",
                background = colorScheme.onError,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Error Container",
                background = colorScheme.errorContainer,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Error Container",
                background = colorScheme.onErrorContainer,
                colorScheme = colorScheme,
            )
        }
        Row(Modifier.fillMaxWidth().weight(1f)) {
            Cell(
                text = "Background",
                background = colorScheme.background,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Background",
                background = colorScheme.onBackground,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Surface",
                background = colorScheme.surface,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Surface",
                background = colorScheme.onSurface,
                colorScheme = colorScheme,
            )
        }
        Row(Modifier.fillMaxWidth().weight(1f)) {
            Cell(
                text = "Outline",
                background = colorScheme.outline,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Surface Container",
                background = colorScheme.surfaceContainer,
                colorScheme = colorScheme,
            )
            Cell(
                text = "Surface-Variant",
                background = colorScheme.surfaceVariant,
                colorScheme = colorScheme,
            )
            Cell(
                text = "On Surface-Variant",
                background = colorScheme.onSurfaceVariant,
                colorScheme = colorScheme,
            )
        }
    }
}

@Composable
fun RowScope.Cell(
    text: String,
    background: Color,
    weight: Float = 1f,
    colorScheme: ColorScheme,
) {
    Box(modifier = Modifier.fillMaxSize().background(background).weight(weight)) {
        Text(text = text, color = colorScheme.contentColorFor(background))
    }
}
