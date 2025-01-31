package com.gilpereda.videomanager.domain

data class AppSettings(
    val mediaSources: List<MediaSource>,
    val videoFileExtensions: Set<String>,
)
