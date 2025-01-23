package com.gilpereda.videomanager.domain

sealed interface VideoFilter {
    fun filter(video: Video): Boolean

    data object Noop : VideoFilter {
        override fun filter(video: Video): Boolean = true
    }
}
