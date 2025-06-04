package com.example.demo.app.api.board.usecase.handler.dto

import com.example.demo.core.board.domain.Board
import java.util.UUID

data class BoardDto(
    val id: UUID,
    val title: String,
    val content: String,
    val tags: List<String>,
    val isFrozen: Boolean,
) {
    companion object {
        fun fromDomain(domain: Board): BoardDto =
            BoardDto(domain.id, domain.title, domain.content, domain.tags, domain.isFrozen)
    }
}
