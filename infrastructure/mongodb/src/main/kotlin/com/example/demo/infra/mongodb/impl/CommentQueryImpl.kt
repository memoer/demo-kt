package com.example.demo.infra.mongodb.impl

import com.example.demo.core.comment.dto.CommentDto
import com.example.demo.core.comment.port.CommentQuery
import com.example.demo.infra.mongodb.repository.CommentRepository
import org.springframework.stereotype.Repository

@Repository
class CommentQueryImpl(private val repository: CommentRepository) : CommentQuery {
    override fun findOne(args: CommentQuery.FindOneArgs): CommentDto = repository.findById(args.id).orElseThrow().toDto()

    override fun findMany(args: CommentQuery.FindManyArgs): List<CommentDto> = repository.findByUserId(args.userId).map { it.toDto() }
}
