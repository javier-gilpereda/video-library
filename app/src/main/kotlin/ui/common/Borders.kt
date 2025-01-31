package com.gilpereda.videomanager.ui.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.borders(
    strokeWidth: Dp,
    color: Color,
    bottom: Boolean = true,
    left: Boolean = true,
    right: Boolean = true,
    top: Boolean = true,
): Modifier =
    then(if (top) topBorder(strokeWidth, color) else Modifier)
        .then(if (bottom) bottomBorder(strokeWidth, color) else Modifier)
        .then(if (left) leftBorder(strokeWidth, color) else Modifier)
        .then(if (right) rightBorder(strokeWidth, color) else Modifier)

fun Modifier.topBorder(
    strokeWidth: Dp,
    color: Color,
) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
            )
        }
    },
)

fun Modifier.bottomBorder(
    strokeWidth: Dp,
    color: Color,
) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx / 2

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
            )
        }
    },
)

fun Modifier.leftBorder(
    strokeWidth: Dp,
    color: Color,
) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = strokeWidthPx / 2
            val height = size.height
            drawLine(
                color = color,
                start = Offset(x = width, y = 0f),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
            )
        }
    },
)

fun Modifier.rightBorder(
    strokeWidth: Dp,
    color: Color,
) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width - strokeWidthPx / 2
            val height = size.height

            drawLine(
                color = color,
                start = Offset(x = width, y = 0f),
                end = Offset(x = width, y = height),
                strokeWidth = strokeWidthPx,
            )
        }
    },
)
