package com.example.demo.core.board.dto

import com.example.demo.core.board.domain.Board
import java.time.LocalDateTime
import java.util.UUID

data class BoardDto(
    val id: UUID?,
    val title: String,
    val content: String,
    val tags: List<String>,
    val isFrozen: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(domain: Board): BoardDto = BoardDto(
            domain.id,
            domain.title,
            domain.content,
            domain.tags,
            domain.isFrozen,
            domain.createdAt,
            domain.updatedAt,
        )
    }
}
