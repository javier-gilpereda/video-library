package com.gilpereda.videomanager.ui.settings

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteVideoFileExtensionDialogUI(
    fileExtension: String,
    onDeleteExtension: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = "Delete video file extension")
        },
        text = {
            Text(text = "Do you really want to delete the media file extension '$fileExtension'?")
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { onDeleteExtension(fileExtension) },
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
