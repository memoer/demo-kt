package com.example.demo.app.api.board.inbound.restful

import com.example.demo.app.api.board.usecase.handler.BoardsGetOneHandler
import com.example.demo.app.api.board.usecase.handler.dto.BoardDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("boards")
class BoardsGetOneController(private val handler: BoardsGetOneHandler) {

    @GetMapping("{id}")
    fun control(@PathVariable id: UUID): BoardDto = handler.handle(BoardsGetOneHandler.Args(id))
}
