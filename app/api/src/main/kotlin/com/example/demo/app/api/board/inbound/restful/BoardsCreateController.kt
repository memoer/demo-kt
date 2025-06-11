package com.example.demo.app.api.board.inbound.restful

import com.example.demo.app.api.board.usecase.handler.BoardsCreateHandler
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("boards")
class BoardsCreateController(private val handler: BoardsCreateHandler) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun control(@Valid @RequestBody request: Request): Unit = BoardsCreateHandler.Args(request.title, request.content, request.tags)
        .run { handler.handle(this) }

    data class Request(
        @Size(min = 1, max = 100) val title: String,
        @Size(min = 1, max = 5_000) val content: String,
        @Size(min = 1, max = 10) val tags: List<String>,
    )
}
