package com.example.demo.infra.redisadapter.entity

import com.example.demo.core.board.domain.Board
import com.example.demo.support.ReflectionUtils
import java.util.UUID

data class BoardEntity(
    var id: UUID?,
    val title: String,
    val content: String,
    val tags: MutableList<String>,
    val isFrozen: Boolean,
) {
    companion object {
        fun fromDomain(board: Board): BoardEntity = BoardEntity(board.id, board.title, board.content, board.tags, board.isFrozen)
    }

    fun toDomain(): Board {
        val entity = Board(title, content, tags)
        ReflectionUtils.setPropertyValue(entity, "id", this.id)
        ReflectionUtils.setPropertyValue(entity, "isFrozen", this.isFrozen)
        return entity
    }
}
