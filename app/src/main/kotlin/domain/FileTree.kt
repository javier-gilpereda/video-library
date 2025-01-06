package com.gilpereda.videomanager.domain

sealed interface FileTreeNode {
    val path: String
    val children: List<ChildTreeNode>
}

data class RootTreeNode(
    override val children: List<ChildTreeNode>,
) : FileTreeNode {
    override val path: String = ""
}

data class ChildTreeNode(
    val parent: FileTreeNode,
    val name: String,
    override val children: List<ChildTreeNode>,
) : FileTreeNode {
    override val path: String by lazy {
        "${parent.path}/$name"
    }
}
