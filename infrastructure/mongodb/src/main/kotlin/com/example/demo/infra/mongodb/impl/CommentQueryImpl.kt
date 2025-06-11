package com.example.demo.infra.mongodb.impl

import com.example.demo.core.comment.dto.CommentDto
import com.example.demo.core.comment.port.CommentQuery
import com.example.demo.infra.mongodb.entity.QCommentEntity
import com.example.demo.infra.mongodb.repository.CommentRepository
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class CommentQueryImpl(
    private val repository: CommentRepository,
    private val operations: MongoOperations,
) : QuerydslRepositorySupport(operations),
    CommentQuery {
    override fun findOne(args: CommentQuery.FindOneArgs): CommentDto {
        val c = QCommentEntity.commentEntity
        val result = from(c).where(c.id.eq(args.id)).fetchOne()
        return result.toDto()
    }

    override fun findMany(args: CommentQuery.FindManyArgs): List<CommentDto> = repository.findByUserId(args.userId).map { it.toDto() }
}
