package com.example.demo.infra.mongodb.entity

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.dto.CommentDto
import com.example.demo.support.util.ReflectionUtils
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "comments")
class CommentEntity(
    val text: String,
    val userId: Long,
) : BaseEntity() {
    companion object {
        fun from(comment: Comment): CommentEntity = CommentEntity(comment.text, comment.userId)
            .apply {
                this.id = comment.id
                this.createdAt = comment.createdAt
                this.updatedAt = comment.updatedAt
            }
    }

    fun toDomain(): Comment {
        val domain = Comment(text, userId)
        ReflectionUtils.setPropertyValue(domain, "id", this.id)
        ReflectionUtils.setPropertyValue(domain, "createdAt", this.createdAt)
        ReflectionUtils.setPropertyValue(domain, "updatedAt", this.updatedAt)
        return domain
    }

    fun toDto(): CommentDto = CommentDto(id ?: "", text, userId, createdAt, updatedAt)
}
