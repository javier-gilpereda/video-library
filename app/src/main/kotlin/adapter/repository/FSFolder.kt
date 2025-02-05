package com.gilpereda.videomanager.adapter.repository

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.domain.VideoFilter
import java.io.File
import java.io.FileFilter

private val notHiddenFolder: FileFilter = FileFilter { file -> !file.name.startsWith(".") && file.isDirectory }

private val notHidden =
    FileFilter { file ->
        !file.name.startsWith(".") && !file.isDirectory
    }

sealed class FSFolder : Folder {
    protected abstract val mediaSourceId: String
    protected abstract val file: File
    override val id: String by lazy { "$mediaSourceId-${file.absolutePath}" }

    fun videos(filter: VideoFilter?): List<Video> =
        file
            .listFiles(notHidden)
            .orEmpty()
            .filter((filter ?: VideoFilter.Noop).toFileFilter::accept)
            .map { FSVideo(it) }

    private data class Root(
        override val mediaSourceId: String,
        override val name: String,
        override val file: File,
    ) : FSFolder() {
        override val children: List<FSFolder> by lazy {
            file
                .listFiles { file: File -> file.isDirectory }
                .orEmpty()
                .map { Node(it, this) }
        }
    }

    private data class Node(
        override val file: File,
        val parent: FSFolder,
    ) : FSFolder() {
        override val mediaSourceId: String by lazy { parent.mediaSourceId }
        override val name: String = file.name
        override val children: List<FSFolder> by lazy {
            file
                .listFiles(notHiddenFolder)
                .orEmpty()
                .map { Node(file = it, parent = this) }
        }
    }

    companion object {
        fun loadTree(mediaSource: MediaSource): Folder =
            File(mediaSource.path)
                .let {
                    when {
                        !it.exists() -> throw IllegalStateException("Root directory ${mediaSource.path} does not exists")
                        !it.isDirectory -> throw IllegalStateException("Root directory ${mediaSource.path} is not a directory")
                        else -> Root(mediaSourceId = mediaSource.id, name = mediaSource.name, file = it)
                    }
                }
    }
}
