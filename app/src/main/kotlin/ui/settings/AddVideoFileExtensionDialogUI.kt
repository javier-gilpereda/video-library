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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun AddVideoFileExtensionDialogUI(
    onAddFileExtension: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var extension by remember { mutableStateOf("") }
    AlertDialog(
        title = {
            Text(text = "Add video file extension")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                TextField(
                    value = extension,
                    onValueChange = { extension = it },
                    label = { Text("Name") },
                )
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            if (extension.isNotBlank()) {
                TextButton(
                    onClick = { onAddFileExtension(extension) },
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
