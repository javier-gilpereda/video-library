package com.gilpereda.videomanager.adapter.filesystem

import com.gilpereda.videomanager.domain.VideoFilter
import java.io.FileFilter

val VideoFilter.toFileFilter
    get() =
        when (this) {
            is VideoFilter.And -> andToFileFilter(this)
            is VideoFilter.SimpleFilter -> simpleToFileFilter(this)
        }

private fun andToFileFilter(and: VideoFilter.And): FileFilter =
    FileFilter { file ->
        and.filters.map { simpleToFileFilter(it) }.all { it.accept(file) }
    }

private fun simpleToFileFilter(filter: VideoFilter.SimpleFilter): FileFilter =
    when (filter) {
        is VideoFilter.Noop -> FileFilter { true }
        is VideoFilter.Extension -> FileFilter { it.extension in filter.extensions }
    }
