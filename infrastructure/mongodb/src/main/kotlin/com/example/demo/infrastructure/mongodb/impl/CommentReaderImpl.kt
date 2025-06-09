package com.example.demo.infrastructure.mongodb.impl

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentReader
import com.example.demo.infrastructure.mongodb.repository.CommentRepository
import org.springframework.stereotype.Repository

@Repository
class CommentReaderImpl(private val repository: CommentRepository) : CommentReader {
    override fun readById(id: String): Comment? = repository.findById(id).orElse(null).toDomain()
}
