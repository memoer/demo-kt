package com.example.demo.app.api.comment.usecase.handler

import com.example.demo.core.comment.port.CommentReader
import com.example.demo.core.comment.port.CommentWriter
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class DeleteCommentHandler(private val commentReader: CommentReader, private val commentWriter: CommentWriter) {
    private val logger = KotlinLogging.logger {}

    fun handle(args: Args) {
        val comment = commentReader.readById(args.id)
        logger.info { "data: ${comment.id}, ${comment.text}, ${comment.userId}" }
        commentWriter.delete(comment)
    }

    data class Args(val id: String)
}
