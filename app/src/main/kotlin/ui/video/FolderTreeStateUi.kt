package com.gilpereda.videomanager.ui.video

import com.gilpereda.videomanager.domain.Folder

data class FolderTreeStateUi(
    val items: List<FolderTreeItemStateUi> = emptyList(),
)

data class FolderTreeItemStateUi(
    val expandable: Boolean,
    val expanded: Boolean,
    val folder: Folder,
    val level: Int,
    val selected: Boolean,
)
