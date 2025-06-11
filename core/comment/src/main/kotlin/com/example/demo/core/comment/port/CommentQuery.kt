package com.example.demo.core.comment.port

import com.example.demo.core.comment.dto.CommentDto

interface CommentQuery {
    fun findOne(args: FindOneArgs): CommentDto
    fun findMany(args: FindManyArgs): List<CommentDto>

    data class FindOneArgs(val id: String)
    data class FindManyArgs(val userId: Long)
}
