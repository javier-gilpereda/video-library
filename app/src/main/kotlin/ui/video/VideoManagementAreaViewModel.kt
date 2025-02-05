package com.gilpereda.videomanager.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilpereda.videomanager.di.ServiceRegistry
import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.service.MediaLibraryManager
import com.gilpereda.videomanager.ui.common.WhileUiSubscribed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class VideoManagementUiState(
    val folderTreeStateUi: FolderTreeStateUi = FolderTreeStateUi(),
    val videoListUIState: VideoListUIState = VideoListUIState(),
)

class VideoManagementAreaViewModel(
    private val mediaLibraryManager: MediaLibraryManager = ServiceRegistry.mediaLibraryManager,
) : ViewModel() {
    private val selectedFolderFlow: MutableStateFlow<Folder?> = MutableStateFlow(null)
    private val folderToExpandedFlow: MutableStateFlow<Map<String, Boolean>> = MutableStateFlow(emptyMap())
    private val selectedVideoFlow: MutableStateFlow<Video?> = MutableStateFlow(null)

    val uiState: StateFlow<VideoManagementUiState> =
        combine(
            mediaLibraryManager.mediaLibrarySource,
            folderToExpandedFlow,
            selectedFolderFlow,
            selectedVideoFlow,
        ) { mediaLibrarySource, folderToExpanded, selectedFolder, selectedVideo ->
            VideoManagementUiState(
                folderTreeStateUi =
                    FolderTreeStateUi(
                        items =
                            folderTreeItems(
                                rootFolders = mediaLibrarySource,
                                folderToExpanded = folderToExpanded,
                                selectedFolder = selectedFolder,
                            ),
                    ),
                videoListUIState =
                    videoListStateUi(
                        selectedFolder = selectedFolder,
                        selectedVideo = selectedVideo,
                    ),
            )
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = VideoManagementUiState(),
        )

    fun onFolderSelected(folder: Folder) {
        selectedFolderFlow.value = folder
    }

    fun onFolderExpandedToggled(folder: Folder) {
        folderToExpandedFlow.value = folderToExpandedFlow.value.toggleState(folder.id)
    }

    fun onVideoSelectedToggled(video: Video) {
        selectedVideoFlow.value = video
    }

    private fun onVideoDeselected() {
        selectedVideoFlow.value = null
    }

    private fun folderTreeItems(
        rootFolders: List<Folder>,
        folderToExpanded: Map<String, Boolean>,
        selectedFolder: Folder?,
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

    private suspend fun videoListStateUi(
        selectedFolder: Folder?,
        selectedVideo: Video?,
    ): VideoListUIState {
        val videos = selectedFolder?.let { mediaLibraryManager.listVideos(it) } ?: emptyList()
        if (selectedVideo !in videos) {
            onVideoDeselected()
        }
        return VideoListUIState(
            itemSize = 200,
            selectedVideo = selectedVideo,
            videos = videos.map { VideoListItemUIState(it, it.name == selectedVideo?.name) },
        )
    }

    private fun List<Folder>.toStateUi(
        level: Int,
        folderToExpanded: Map<String, Boolean>,
        selectedFolder: Folder?,
    ): List<FolderTreeItemStateUi> =
        map { folder ->
            FolderTreeItemStateUi(
                expandable = folder.children.isNotEmpty(),
                expanded = folderToExpanded[folder.id] ?: false,
                folder = folder,
                level = level,
                selected = folder.id == selectedFolder?.id,
            )
        }

    private fun Map<String, Boolean>.toggleState(id: String): Map<String, Boolean> = this + mapOf(id to !(get(id) ?: false))
}
