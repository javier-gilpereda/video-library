package com.gilpereda.videomanager.ui.settings

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

@Composable
fun VideoFileExtensionsUI(
    videoFileExtensionsFlow: Flow<Set<String>>,
    onExtensionAdded: suspend (String) -> Unit,
    onExtensionRemoved: suspend (String) -> Unit,
) {
}
