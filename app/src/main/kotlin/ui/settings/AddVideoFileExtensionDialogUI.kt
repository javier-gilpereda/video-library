package com.gilpereda.videomanager.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AddVideoFileExtensionDialogUI(
    onAddFileExtension: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var extension by remember { mutableStateOf("") }
    EditSettingDialogUI(
        title = "Add video file extension",
        onDismiss = onDismiss,
        onConfirm = { onAddFileExtension(extension) },
        showConfirmButton = extension.isNotBlank(),
    ) {
        Column {
            TextField(
                value = extension,
                onValueChange = { extension = it },
                label = { Text("Name") },
            )
        }
    }
}
