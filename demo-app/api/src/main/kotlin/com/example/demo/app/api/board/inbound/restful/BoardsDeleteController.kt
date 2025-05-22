package com.example.demo.app.api.board.inbound.restful

import com.example.demo.app.api.board.usecase.handler.BoardsDeleteHandler
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("boards")
class BoardsDeleteController(private val handler: BoardsDeleteHandler) {

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun control(@PathVariable id: UUID): Unit = handler.handle(id)
}
