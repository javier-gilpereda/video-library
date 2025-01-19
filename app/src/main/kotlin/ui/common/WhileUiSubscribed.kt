package com.gilpereda.videomanager.ui.common

import kotlinx.coroutines.flow.SharingStarted

private const val STOP_TIMEOUT_MS: Long = 5000

val WhileUiSubscribed: SharingStarted = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS)
