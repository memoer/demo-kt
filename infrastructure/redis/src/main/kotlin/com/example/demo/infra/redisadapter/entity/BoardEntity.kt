package com.example.demo.infra.redisadapter.entity

import com.example.demo.core.board.domain.Board
import com.example.demo.support.util.ReflectionUtils

data class BoardEntity(
    val title: String,
    val content: String,
    val tags: MutableList<String>,
    val isFrozen: Boolean,
) : BaseEntity() {
    companion object {
        fun from(board: Board) = BoardEntity(board.title, board.content, board.tags, board.isFrozen)
            .apply {
                this.id = board.id
                this.createdAt = board.createdAt
                this.updatedAt = board.updatedAt
            }
    }

    fun toDomain(): Board {
        val domain = Board(title, content, tags, isFrozen)
        ReflectionUtils.setPropertyValue(domain, "id", this.id)
        ReflectionUtils.setPropertyValue(domain, "createdAt", this.createdAt)
        ReflectionUtils.setPropertyValue(domain, "updatedAt", this.updatedAt)
        return domain
    }
}
