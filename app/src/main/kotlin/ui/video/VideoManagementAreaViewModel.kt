package com.gilpereda.videomanager.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilpereda.videomanager.di.ServiceRegistry
import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.service.MediaLibraryManager
import com.gilpereda.videomanager.service.ports.ApplicationSettings
import com.gilpereda.videomanager.ui.common.WhileUiSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

private const val FOLDER_TO_EXPANDED = "folder_to_expanded"
private const val SELECTED_FOLDER = "selected_folder"

data class FolderTreeItemStateUi(
    val expandable: Boolean,
    val expanded: Boolean,
    val folder: Folder,
    val level: Int,
    val selected: Boolean,
)

data class FolderTreeStateUi(
    val items: List<FolderTreeItemStateUi> = emptyList(),
)

data class VideoManagementUiState(
    val folderTressStateUi: FolderTreeStateUi = FolderTreeStateUi(),
)

class VideoManagementAreaViewModel(
    mediaLibraryManager: MediaLibraryManager = ServiceRegistry.mediaLibraryManager,
    private val settings: ApplicationSettings,
) : ViewModel() {
    val uiState: StateFlow<VideoManagementUiState> =
        combine(
            mediaLibraryManager.mediaLibrarySource,
            folderToExpandedStateFlow(),
            selectedFolderStateFlow(),
        ) { mediaLibrarySource, folderToExpanded, selectedFolder ->
            VideoManagementUiState(FolderTreeStateUi(folderTreeItems(mediaLibrarySource, folderToExpanded, selectedFolder)))
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = VideoManagementUiState(),
        )

    fun onFolderSelected(folder: Folder) {
        settings[SELECTED_FOLDER] = folder.id
    }

    fun onFolderExpandedToggled(folder: Folder) {
        settings[FOLDER_TO_EXPANDED] = folderToExpandedStateFlow().value.toggleState(folder.id)
    }

    private fun folderToExpandedStateFlow(): StateFlow<Map<String, Boolean>> = settings.getStateFlow(FOLDER_TO_EXPANDED, emptyMap())

    private fun selectedFolderStateFlow(): StateFlow<String?> = settings.getStateFlow(SELECTED_FOLDER, null)

    private fun folderTreeItems(
        rootFolders: List<Folder>,
        folderToExpanded: Map<String, Boolean>,
        selectedFolder: String?,
    ): List<FolderTreeItemStateUi> {
        tailrec fun go(
            rest: List<FolderTreeItemStateUi>,
            items: List<FolderTreeItemStateUi> = emptyList(),
        ): List<FolderTreeItemStateUi> =
            if (rest.isNotEmpty()) {
                val current = rest.first()
                if (current.expanded) {
                    val newRest = current.folder.children.toStateUi(current.level + 1, folderToExpanded, selectedFolder) + rest.drop(1)
                    go(newRest, items + current)
                } else {
                    go(rest.drop(1), items + current)
                }
            } else {
                items
            }
        return go(rootFolders.toStateUi(0, folderToExpanded, selectedFolder))
    }

    private fun List<Folder>.toStateUi(
        level: Int,
        folderToExpanded: Map<String, Boolean>,
        selectedFolder: String?,
    ): List<FolderTreeItemStateUi> =
        map { folder ->
            FolderTreeItemStateUi(
                expandable = folder.children.isNotEmpty(),
                expanded = folderToExpanded[folder.id] ?: false,
                folder = folder,
                level = level,
                selected = folder.id == selectedFolder,
            )
        }

    private fun Map<String, Boolean>.toggleState(id: String): Map<String, Boolean> = this + mapOf(id to !(get(id) ?: false))
}
