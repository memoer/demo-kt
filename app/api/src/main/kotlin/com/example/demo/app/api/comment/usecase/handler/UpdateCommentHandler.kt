package com.example.demo.app.api.comment.usecase.handler

import com.example.demo.core.comment.port.CommentReader
import com.example.demo.core.comment.port.CommentWriter
import org.springframework.stereotype.Service

@Service
class UpdateCommentHandler(private val commentReader: CommentReader, private val commentWriter: CommentWriter) {
    fun handle(args: Args) {
        val comment = commentReader.readById(args.id)
        comment.changeText(args.text)
        commentWriter.write(comment)
    }

    data class Args(val id: String, val text: String)
}
