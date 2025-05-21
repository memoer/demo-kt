package com.example.demo.interf.api.restful

import com.example.demo.usecase.handler.__dto__.BoardDto
import com.example.demo.usecase.handler.BoardsGetOneHandler
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