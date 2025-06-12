package com.example.demo.infra.mongodb.entity

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.dto.CommentDto
import com.example.demo.support.util.ReflectionUtils
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "comments")
class CommentEntity(
    @Id
    val id: String?,
    val text: String,
    val userId: Long,
) {
    companion object {
        fun from(comment: Comment): CommentEntity = CommentEntity(comment.id, comment.text, comment.userId)
    }

    fun toDomain(): Comment = Comment(text, userId).apply {
        ReflectionUtils.setPropertyValue(this, "id", this@CommentEntity.id ?: "")
    }

    fun toDto(): CommentDto = CommentDto(id ?: "", text, userId)
}
