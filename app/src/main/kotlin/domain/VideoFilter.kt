package com.gilpereda.videomanager.domain

sealed interface VideoFilter {
    operator fun plus(other: VideoFilter): VideoFilter

    sealed interface SimpleFilter : VideoFilter {
        override fun plus(other: VideoFilter): VideoFilter =
            when (other) {
                is And -> other + this
                is SimpleFilter -> And(listOf(this, other))
            }
    }

    data object Noop : SimpleFilter

    data class Extension(
        val extensions: Collection<String>,
    ) : SimpleFilter

    data class And(
        val filters: Collection<SimpleFilter>,
    ) : VideoFilter {
        override fun plus(other: VideoFilter): VideoFilter =
            when (other) {
                is And -> And(filters + other.filters)
                is SimpleFilter -> And(filters + other)
            }
    }
}
