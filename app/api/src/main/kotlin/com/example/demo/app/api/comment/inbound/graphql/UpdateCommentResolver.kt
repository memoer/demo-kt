package com.example.demo.app.api.comment.inbound.graphql

import com.example.demo.app.api.comment.usecase.handler.UpdateCommentHandler
import com.example.demo.app.api.graphql.types.UpdateCommentInput
import com.example.demo.library.security.annotation.AuthRequired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class UpdateCommentResolver(private val updateCommentHandler: UpdateCommentHandler) {
    @MutationMapping("updateComment")
    @AuthRequired
    fun resolve(@Argument input: UpdateCommentInput): Boolean {
        val args = UpdateCommentHandler.Args(input.id, input.text)
        updateCommentHandler.handle(args)
        return true
    }
}
