package com.gilpereda.videomanager.ui.settings

import androidx.compose.runtime.Composable
import com.gilpereda.videomanager.domain.MediaSource

@Composable
fun DeleteMediaSourceDialogUI(
    mediaSource: MediaSource,
    onDeleteMediaSource: (MediaSource) -> Unit,
    onDismiss: () -> Unit,
) {
    DeleteSettingDialogUI(
        title = "Delete media source",
        confirmMessage = "Do you really want to delete the media source '${mediaSource.name}'?",
        onDismiss = onDismiss,
        onConfirm = { onDeleteMediaSource(mediaSource) },
    )
}
