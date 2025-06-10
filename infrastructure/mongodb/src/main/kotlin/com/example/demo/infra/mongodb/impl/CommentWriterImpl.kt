package com.example.demo.infra.mongodb.impl

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentWriter
import com.example.demo.infra.mongodb.entity.CommentEntity
import com.example.demo.infra.mongodb.repository.CommentRepository
import org.springframework.stereotype.Repository

@Repository
class CommentWriterImpl(private val repository: CommentRepository) : CommentWriter {
    override fun write(comment: Comment) {
        repository.save(CommentEntity.fromDomain(comment))
    }

    override fun delete(comment: Comment) {
        repository.delete(CommentEntity.fromDomain(comment))
    }
}
