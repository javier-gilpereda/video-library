package com.gilpereda.videomanager.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.gilpereda.videomanager.domain.MediaSource

class EditMediaSourceDialogUiState(
    private val _mediaSource: MediaSource?,
) {
    var name by mutableStateOf(_mediaSource?.name ?: "")
    var path by mutableStateOf(_mediaSource?.path ?: "")

    val valid: Boolean
        get() = name.isNotBlank() && path.isNotBlank()

    val mediaSource: MediaSource
        get() = _mediaSource?.copy(name = name, path = path) ?: MediaSource.create(name, path)
}

fun rememberMovieEditorState(mediaSource: MediaSource?): EditMediaSourceDialogUiState = EditMediaSourceDialogUiState(mediaSource)

@Composable
fun EditMediaSourceDialogUI(
    uiState: EditMediaSourceDialogUiState,
    onEditMediaSource: (MediaSource) -> Unit,
    onDismiss: () -> Unit,
) {
    val title = "Edit media source"
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                TextField(
                    value = uiState.name,
                    onValueChange = { uiState.name = it },
                    label = { Text("Name") },
                )
                TextField(
                    value = uiState.path,
                    onValueChange = { uiState.path = it },
                    label = { Text("Path") },
                )
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            if (uiState.valid) {
                TextButton(
                    onClick = { onEditMediaSource(uiState.mediaSource) },
                ) {
                    Text("Confirm")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
            ) {
                Text("Dismiss")
            }
        },
    )
}
