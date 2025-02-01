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
import com.gilpereda.videomanager.ui.common.borders
import com.gilpereda.videomanager.ui.components.EditableTable
import com.gilpereda.videomanager.ui.components.TableViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun VideoFileExtensionsUI(
    videoFileExtensionsFlow: Flow<Set<String>>,
    onExtensionAdded: suspend (String) -> Unit,
    onExtensionRemoved: suspend (String) -> Unit,
) {
    val videoFileExtensions by videoFileExtensionsFlow.collectAsState(setOf())
    var addFileExtension: Boolean by remember { mutableStateOf(false) }
    var deleteFileExtension: String? by remember { mutableStateOf(null) }

    val coroutineScope = rememberCoroutineScope()
    Surface {
        Column(
            Modifier
                .borders(1.dp, MaterialTheme.colorScheme.outlineVariant, bottom = false),
        ) {
            EditableTable(
                viewModel =
                    TableViewModel(
                        tableName = "Video File Extensions",
                        headers = listOf("Extension"),
                        data = videoFileExtensions.toList(),
                        columnWeights = listOf(1f),
                        itemToRow = { listOf(this) },
                    ),
                onAddItem = { addFileExtension = true },
                onRemoveItem = { deleteFileExtension = it },
            )
        }
    }
    when {
        deleteFileExtension != null -> {
            DeleteVideoFileExtensionDialogUI(
                fileExtension = deleteFileExtension!!,
                onDeleteExtension = {
                    coroutineScope.launch { onExtensionRemoved(it) }
                    deleteFileExtension = null
                },
                onDismiss = { deleteFileExtension = null },
            )
        }
        addFileExtension -> {
            AddVideoFileExtensionDialogUI(
                onAddFileExtension = {
                    coroutineScope.launch { onExtensionAdded(it) }
                    addFileExtension = false
                },
                onDismiss = { addFileExtension = false },
            )
        }
    }
}
