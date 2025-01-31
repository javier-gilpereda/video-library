package com.gilpereda.videomanager.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.ui.common.borders
import com.gilpereda.videomanager.ui.components.EditableTable
import com.gilpereda.videomanager.ui.components.TableViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val BLANK = ""

// TODO: add check for existing folder
@Composable
fun MediaSourcesUI(
    mediaSourcesFlow: Flow<List<MediaSource>>,
    onMediaSourceAdded: suspend (MediaSource) -> Unit,
    onMediaSourceRemoved: suspend (MediaSource) -> Unit,
    onMediaSourceUpdated: suspend (MediaSource) -> Unit,
) {
    val mediaSources by mediaSourcesFlow.collectAsState(emptyList())
    var addMediaSource: MediaSource? by remember { mutableStateOf(null) }
    var deleteMediaSource: MediaSource? by remember { mutableStateOf(null) }
    var editMediaSource: MediaSource? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()
    Surface {
        Column(
            Modifier
                .borders(1.dp, MaterialTheme.colorScheme.outlineVariant, bottom = false),
        ) {
            EditableTable(
                viewModel =
                    TableViewModel(
                        tableName = "Media Sources",
                        headers = listOf("Name", "Path"),
                        data = mediaSources,
                        columnWeights = listOf(1f, 2f),
                        itemToRow = { listOf(name, path) },
                    ),
                onAddItem = { addMediaSource = MediaSource.create(BLANK, BLANK) },
                onRemoveItem = { deleteMediaSource = it },
                onEditItem = { editMediaSource = it },
            )
        }
    }
    when {
        deleteMediaSource != null -> {
            DeleteMediaSourceDialogUI(
                mediaSource = deleteMediaSource!!,
                onDeleteMediaSource = {
                    coroutineScope.launch { onMediaSourceRemoved(it) }
                    deleteMediaSource = null
                },
                onDismiss = { deleteMediaSource = null },
            )
        }
        editMediaSource != null -> {
            EditMediaSourceDialogUI(
                uiState = rememberMovieEditorState(editMediaSource!!),
                onEditMediaSource = {
                    coroutineScope.launch { onMediaSourceUpdated(it) }
                    editMediaSource = null
                },
                onDismiss = { editMediaSource = null },
            )
        }
        addMediaSource != null -> {
            EditMediaSourceDialogUI(
                uiState = rememberMovieEditorState(null),
                onEditMediaSource = {
                    coroutineScope.launch { onMediaSourceAdded(it) }
                    addMediaSource = null
                },
                onDismiss = { addMediaSource = null },
            )
        }
    }
}
