package com.example.demo.core.comment.dto

import com.example.demo.core.comment.domain.Comment
import java.time.LocalDateTime

data class CommentDto(
    val id: String?,
    var text: String,
    var userId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(domain: Comment): CommentDto = CommentDto(domain.id, domain.text, domain.userId, domain.createdAt, domain.updatedAt)
    }
}
