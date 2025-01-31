package com.gilpereda.videomanager.ui.settings

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.gilpereda.videomanager.domain.MediaSource

@Composable
fun DeleteMediaSourceDialogUI(
    mediaSource: MediaSource,
    onDeleteMediaSource: (MediaSource) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = "Delete media source")
        },
        text = {
            Text(text = "Do you really want to delete the media source '${mediaSource.name}'?")
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { onDeleteMediaSource(mediaSource) },
            ) {
                Text("Confirm")
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
