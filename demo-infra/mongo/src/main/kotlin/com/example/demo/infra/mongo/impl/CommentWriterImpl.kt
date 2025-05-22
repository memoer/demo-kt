package com.example.demo.infra.mongo.impl

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentWriter
import com.example.demo.infra.mongo.entity.CommentEntity
import com.example.demo.infra.mongo.repository.CommentRepository
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
