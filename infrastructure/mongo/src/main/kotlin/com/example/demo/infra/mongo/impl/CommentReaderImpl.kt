package com.example.demo.infra.mongo.impl

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentReader
import com.example.demo.infra.mongo.repository.CommentRepository
import org.springframework.stereotype.Repository

@Repository
class CommentReaderImpl(private val repository: CommentRepository) : CommentReader {
    override fun readById(id: String): Comment? = repository.findById(id).orElse(null).toDomain()
}
