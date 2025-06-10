package com.example.demo.infra.mongodbadapter.impl

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentWriter
import com.example.demo.infra.mongodbadapter.entity.CommentEntity
import com.example.demo.infra.mongodbadapter.repository.CommentRepository
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
