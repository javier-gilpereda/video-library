package com.gilpereda.videomanager.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class PanelState(
    expanded: Boolean = true,
    expandedSize: Dp = 400.dp,
) {
    val collapsedSize = 24.dp
    var expandedSize by mutableStateOf(expandedSize)
    val expandedSizeMin = 90.dp
    var isExpanded by mutableStateOf(expanded)
    val splitter: SplitterState = SplitterState()
}

class SplitterState {
    var isResizing by mutableStateOf(false)
    var isResizeEnabled by mutableStateOf(true)
}

@Composable
fun ResizablePanel(
    modifier: Modifier,
    state: PanelState,
    content: @Composable () -> Unit,
) {
    val alpha by animateFloatAsState(if (state.isExpanded) 1f else 0f, SpringSpec(stiffness = Spring.StiffnessLow))

    Box(modifier) {
        Box(Modifier.fillMaxSize().graphicsLayer(alpha = alpha)) {
            content()
        }

        Icon(
            if (state.isExpanded) Icons.AutoMirrored.Default.ArrowBackIos else Icons.AutoMirrored.Default.ArrowForwardIos,
            contentDescription = if (state.isExpanded) "Collapse" else "Expand",
            tint = LocalContentColor.current,
            modifier =
                Modifier
                    .padding(top = 4.dp)
                    .width(24.dp)
                    .clickable {
                        state.isExpanded = !state.isExpanded
                    }.padding(4.dp)
                    .align(Alignment.TopEnd),
        )
    }
}

@Composable
fun VerticalSplittable(
    modifier: Modifier,
    panelState: PanelState,
    leftPanel: @Composable () -> Unit,
    rightPanel: @Composable () -> Unit,
) {
    val animatedSize =
        if (panelState.splitter.isResizing) {
            if (panelState.isExpanded) panelState.expandedSize else panelState.collapsedSize
        } else {
            animateDpAsState(
                if (panelState.isExpanded) panelState.expandedSize else panelState.collapsedSize,
                SpringSpec(stiffness = Spring.StiffnessLow),
            ).value
        }

    Box(
        Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Layout(
            content = {
                ResizablePanel(Modifier.width(animatedSize).fillMaxHeight(), panelState, leftPanel)
                rightPanel()
                VerticalSplitter(panelState.splitter, {
                    panelState.expandedSize = (panelState.expandedSize + it).coerceAtLeast(panelState.expandedSizeMin)
                })
            },
            modifier = modifier,
            measurePolicy = { measurables, constraints ->
                require(measurables.size == 3)

                val firstPlaceable = measurables[0].measure(constraints.copy(minWidth = 0))
                val secondWidth = constraints.maxWidth - firstPlaceable.width
                val secondPlaceable =
                    measurables[1].measure(
                        Constraints(
                            minWidth = secondWidth,
                            maxWidth = secondWidth,
                            minHeight = constraints.maxHeight,
                            maxHeight = constraints.maxHeight,
                        ),
                    )
                val splitterPlaceable = measurables[2].measure(constraints)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    firstPlaceable.place(0, 0)
                    secondPlaceable.place(firstPlaceable.width, 0)
                    splitterPlaceable.place(firstPlaceable.width, 0)
                }
            },
        )
    }
}

@Composable
fun VerticalSplitter(
    splitterState: SplitterState,
    onResize: (delta: Dp) -> Unit,
    color: Color = MaterialTheme.colorScheme.background,
) = Box {
    val density = LocalDensity.current
    Box(
        Modifier
            .width(8.dp)
            .fillMaxHeight()
            .pointerHoverIcon(PointerIcon.Crosshair)
            .run {
                if (splitterState.isResizeEnabled) {
                    this.draggable(
                        state =
                            rememberDraggableState {
                                with(density) {
                                    onResize(it.toDp())
                                }
                            },
                        orientation = Orientation.Horizontal,
                        startDragImmediately = true,
                        onDragStarted = { splitterState.isResizing = true },
                        onDragStopped = { splitterState.isResizing = false },
                    )
                } else {
                    this
                }
            },
    )

    Box(
        Modifier
            .width(1.dp)
            .fillMaxHeight()
            .background(color),
    )
}
