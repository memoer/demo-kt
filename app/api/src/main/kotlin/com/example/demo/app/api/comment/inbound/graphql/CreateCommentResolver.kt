package com.example.demo.app.api.comment.inbound.graphql

import com.example.demo.app.api.comment.usecase.handler.CreateCommentHandler
import com.example.demo.app.api.graphql.types.CreateCommentInput
import com.example.demo.library.security.annotation.AuthRequired
import com.example.demo.library.security.annotation.CurrentUser
import com.example.demo.support.type.OnuiiUser
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class CreateCommentResolver(private val createCommentHandler: CreateCommentHandler) {
    @MutationMapping("createComment")
    @AuthRequired
    fun resolve(@Argument input: CreateCommentInput, @CurrentUser user: OnuiiUser): Boolean {
        val args = CreateCommentHandler.Args(input.text, user.id)
        createCommentHandler.handle(args)
        return true
    }
}
