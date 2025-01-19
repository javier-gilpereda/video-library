package com.gilpereda.videomanager.adapter.filesystem

import com.gilpereda.videomanager.domain.Folder
import com.gilpereda.videomanager.domain.MediaSource
import java.io.File

sealed interface FSFolder : Folder {
    private data class Root(
        override val name: String,
        val file: File,
    ) : FSFolder {
        override val id: String = file.absolutePath
        override val children: List<FSFolder> by lazy {
            file
                .listFiles { file: File -> file.isDirectory }
                .orEmpty()
                .map { Node(it, this) }
        }
    }

    private data class Node(
        val file: File,
        val parent: FSFolder,
    ) : FSFolder {
        override val name: String = file.name
        override val children: List<FSFolder> by lazy {
            file
                .listFiles { file: File -> file.isDirectory }
                .orEmpty()
                .map { Node(file = it, parent = this) }
        }

        override val id: String = file.absolutePath
    }

    companion object {
        fun loadTree(mediaSource: MediaSource): Folder =
            File(mediaSource.path)
                .let {
                    when {
                        !it.exists() -> throw IllegalStateException("Root directory ${mediaSource.path} does not exists")
                        !it.isDirectory -> throw IllegalStateException("Root directory ${mediaSource.path} is not a directory")
                        else -> Root(name = mediaSource.name, file = it)
                    }
                }
    }
}
