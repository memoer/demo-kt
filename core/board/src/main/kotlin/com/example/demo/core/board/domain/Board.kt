package com.example.demo.core.board.domain

import java.util.UUID

class Board {
    var id: UUID? = null
        private set
    var title: String private set
    var content: String private set
    var tags: MutableList<String> private set
    var isFrozen: Boolean private set

    constructor(title: String, content: String, tags: MutableList<String>) {
        this.title = title
        this.content = content
        this.tags = tags
        this.isFrozen = false
    }

    fun freeze() {
        this.isFrozen = true
    }

    fun changeTitle(title: String) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        this.title = title
    }

    fun changeContent(content: String) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        this.content = content
    }

    fun addTags(tags: List<String>) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        if (this.tags.size + tags.size > 100) {
            throw RuntimeException("the boundary of tags size is 100")
        }
        this.tags.addAll(tags)
    }

    fun deleteTags(tags: List<String>) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        this.tags = this.tags.filter { it !in tags }.toMutableList()
    }
}
