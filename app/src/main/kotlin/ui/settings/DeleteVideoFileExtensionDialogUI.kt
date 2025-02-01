package com.gilpereda.videomanager.ui.settings

import androidx.compose.runtime.Composable

@Composable
fun DeleteVideoFileExtensionDialogUI(
    fileExtension: String,
    onDeleteExtension: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    DeleteSettingDialogUI(
        title = "Delete video file extension",
        confirmMessage = "Do you really want to delete the media file extension '$fileExtension'?",
        onDismiss = onDismiss,
        onConfirm = { onDeleteExtension(fileExtension) },
    )
}
