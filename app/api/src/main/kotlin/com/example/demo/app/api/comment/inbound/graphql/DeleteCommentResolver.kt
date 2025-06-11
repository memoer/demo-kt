package com.example.demo.app.api.comment.inbound.graphql

import com.example.demo.app.api.comment.usecase.handler.DeleteCommentHandler
import com.example.demo.app.api.graphql.types.DeleteCommentInput
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class DeleteCommentResolver(private val deleteCommentHandler: DeleteCommentHandler) {
    @MutationMapping("deleteComment")
    fun resolve(@Argument input: DeleteCommentInput): Boolean {
        val args = DeleteCommentHandler.Args(input.id)
        deleteCommentHandler.handle(args)
        return true
    }
}
