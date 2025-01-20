package com.gilpereda.videomanager.ui.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.ui.common.VerticalScrollbar
import com.gilpereda.videomanager.ui.common.withoutWidthConstraints

const val FILE_TREE = "file_tree"

@Composable
fun FolderTreeUI(
    uiState: VideoManagementUiState,
    onFolderSelected: (Folder) -> Unit,
    onFolderExpandedToggled: (Folder) -> Unit,
) {
    Surface(
        Modifier
            .fillMaxSize(),
    ) {
        with(LocalDensity.current) {
            Box {
                val scrollState = rememberLazyListState()

                LazyColumn(
                    modifier = Modifier.fillMaxSize().withoutWidthConstraints(),
                    state = scrollState,
                ) {
                    items(uiState.folderTressStateUi.items.size) {
                        val folderStateUi = uiState.folderTressStateUi.items[it]
                        FolderTreeItemUI(
                            fontSize = 14.sp,
                            height = 14.sp.toDp() * 2f,
                            onClicked = { onFolderSelected(folderStateUi.folder) },
                            onToggleExpanded = { onFolderExpandedToggled(folderStateUi.folder) },
                            stateUi = folderStateUi,
                        )
                    }
                }
                VerticalScrollbar(
                    Modifier.align(Alignment.CenterEnd),
                    scrollState,
                )
            }
        }
    }
}

@Composable
private fun FolderTreeItemUI(
    fontSize: TextUnit,
    height: Dp,
    onClicked: () -> Unit,
    onToggleExpanded: () -> Unit,
    stateUi: FolderTreeItemStateUi,
) {
    Row(
        modifier =
            Modifier
                .wrapContentHeight()
                .padding(start = 24.dp * stateUi.level)
                .clickable { onClicked() }
                .height(height)
                .fillMaxWidth(),
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val active by interactionSource.collectIsHoveredAsState()

        TreeExpandIcon(
            modifier = Modifier.align(Alignment.CenterVertically).size(height),
            onClicked = onToggleExpanded,
            stateUi = stateUi,
        )
        FolderIcon(modifier = Modifier.align(Alignment.CenterVertically).size(height))
        Text(
            text = stateUi.folder.name,
            color = if (active) LocalContentColor.current.copy(alpha = 0.60f) else LocalContentColor.current,
            modifier =
                Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clipToBounds()
                    .hoverable(interactionSource),
            softWrap = true,
            fontSize = fontSize,
            fontStyle = if (stateUi.selected) FontStyle.Italic else FontStyle.Normal,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Composable
fun TreeExpandIcon(
    modifier: Modifier,
    onClicked: () -> Unit,
    stateUi: FolderTreeItemStateUi,
) = Box(
    modifier
        .let { if (stateUi.expandable) it.clickable { onClicked() } else it }
        .padding(4.dp),
) {
    when {
        !stateUi.expandable -> Unit
        stateUi.expanded ->
            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = LocalContentColor.current)
        else ->
            Icon(Icons.AutoMirrored.Default.KeyboardArrowRight, contentDescription = null, tint = LocalContentColor.current)
    }
}

@Composable
private fun FolderIcon(modifier: Modifier) =
    Box(modifier.padding(4.dp)) {
        Icon(
            Icons.Default.Folder,
            contentDescription = null,
            tint = LocalContentColor.current,
        )
    }
