package com.example.demo.core.comment.dto

import com.example.demo.core.comment.domain.Comment

data class CommentDto(val id: String?, var text: String, var userId: Long) {
    companion object {
        fun from(domain: Comment): CommentDto = CommentDto(domain.id, domain.text, domain.userId)
    }
}
