package com.example.demo.infra.mongodb.impl

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentWriter
import com.example.demo.infra.mongodb.entity.CommentEntity
import com.example.demo.infra.mongodb.repository.CommentRepository
import com.example.demo.support.util.ReflectionUtils
import org.springframework.stereotype.Repository

@Repository
class CommentWriterImpl(private val repository: CommentRepository) : CommentWriter {
    override fun write(comment: Comment) {
        val saved = CommentEntity.from(comment).run { repository.save(this) }
        ReflectionUtils.setPropertyValue(comment, "id", saved.id)
    }

    override fun delete(comment: Comment) {
        CommentEntity.from(comment).run { repository.delete(this) }
    }
}
