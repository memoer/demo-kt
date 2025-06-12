package com.example.demo.core.board.domain

import com.example.demo.core.common.domain.Base
import java.time.LocalDateTime
import java.util.UUID

class Board : Base<UUID> {
    var title: String private set
    var content: String private set
    var tags: MutableList<String> private set
    var isFrozen: Boolean private set

    constructor(
        title: String,
        content: String,
        tags: MutableList<String>,
        isFrozen: Boolean,
    ) : super(UUID.randomUUID()) {
        this.title = title
        this.content = content
        this.tags = tags
        this.isFrozen = isFrozen
    }

    fun freeze() {
        this.isFrozen = true
    }

    fun changeTitle(title: String) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        this.title = title
        this.updatedAt = LocalDateTime.now()
    }

    fun changeContent(content: String) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        this.content = content
        this.updatedAt = LocalDateTime.now()
    }

    fun addTags(tags: List<String>) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        if (this.tags.size + tags.size > 100) {
            throw RuntimeException("the boundary of tags size is 100")
        }
        this.tags.addAll(tags)
        this.updatedAt = LocalDateTime.now()
    }

    fun deleteTags(tags: List<String>) {
        if (this.isFrozen) {
            throw RuntimeException("$id is frozen")
        }
        this.tags = this.tags.filter { it !in tags }.toMutableList()
        this.updatedAt = LocalDateTime.now()
    }
}
