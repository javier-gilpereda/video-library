package com.gilpereda.videomanager.ui.video

import com.gilpereda.videomanager.domain.Video

data class VideoListUIState(
    val videos: List<VideoListItemUIState> = emptyList(),
    val itemSize: Int = 200,
)

data class VideoListItemUIState(
    val video: Video,
    val selected: Boolean,
)
