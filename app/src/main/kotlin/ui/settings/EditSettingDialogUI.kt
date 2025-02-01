package com.gilpereda.videomanager.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun EditSettingDialogUI(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    showConfirmButton: Boolean,
    content: @Composable () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                content()
            }
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            if (showConfirmButton) {
                TextButton(
                    onClick = onConfirm,
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
