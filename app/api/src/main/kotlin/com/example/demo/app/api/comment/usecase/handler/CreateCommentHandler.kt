package com.example.demo.app.api.comment.usecase.handler

import com.example.demo.core.comment.domain.Comment
import com.example.demo.core.comment.port.CommentWriter
import org.springframework.stereotype.Service

@Service
class CreateCommentHandler(private val commentWriter: CommentWriter) {
    fun handle(args: Args) {
        val comment = Comment(args.text, args.userId)
        commentWriter.write(comment)
    }

    data class Args(val text: String, val userId: Long)
}
