package com.example.demo.infra.mongodb.entity

import com.example.demo.core.comment.domain.Comment
import com.example.demo.support.ReflectionUtils
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "comments")
class CommentEntity(
    @Id
    internal val id: String? = null,
    internal val text: String,
    internal val userId: Long,
) {
    companion object {
        fun fromDomain(comment: Comment): CommentEntity = CommentEntity(comment.id, comment.text, comment.userId)
    }

    fun toDomain(): Comment = Comment(text, userId).apply {
        ReflectionUtils.setPropertyValue(this, "id", this@CommentEntity.id ?: -1L)
    }
}
