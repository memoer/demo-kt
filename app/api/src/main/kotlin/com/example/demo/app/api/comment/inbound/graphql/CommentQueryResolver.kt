package com.example.demo.app.api.comment.inbound.graphql

import com.example.demo.app.api.graphql.types.CommentModel
import com.example.demo.app.api.graphql.types.GetCommentOneInput
import com.example.demo.app.api.graphql.types.GetCommentsInput
import com.example.demo.core.comment.port.CommentQuery
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.ZoneOffset

@Controller
class CommentQueryResolver(private val commentQuery: CommentQuery) {
    @QueryMapping
    fun getCommentOne(@Argument input: GetCommentOneInput): CommentModel = CommentQuery.FindOneArgs(input.id)
        .run { commentQuery.findOne(this) }
        .let { CommentModel(it.id!!, it.text, it.userId, it.createdAt.atOffset(ZoneOffset.UTC), it.updatedAt.atOffset(ZoneOffset.UTC)) }

    @QueryMapping
    fun getComments(@Argument input: GetCommentsInput): List<CommentModel> = CommentQuery.FindManyArgs(input.userId)
        .run { commentQuery.findMany(this) }
        .map { CommentModel(it.id!!, it.text, it.userId, it.createdAt.atOffset(ZoneOffset.UTC), it.updatedAt.atOffset(ZoneOffset.UTC)) }
}
