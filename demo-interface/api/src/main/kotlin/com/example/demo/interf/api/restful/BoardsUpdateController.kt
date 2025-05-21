package com.example.demo.interf.api.restful

import com.example.demo.usecase.handler.BoardsUpdateHandler
import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("boards")
class BoardsUpdateController(private val handler: BoardsUpdateHandler) {

    @PutMapping("{id}")
    fun control(@PathVariable id: UUID, @Valid @RequestBody request: Request) =
        BoardsUpdateHandler.Args(
            request.title,
            request.content,
            request.tagsToAdd,
            request.tagsToDelete,
            request.isFrozen
        )
            .run { handler.handle(id, this) }


    data class Request(
        @Size(min = 1, max = 100) val title: String?,
        @Size(min = 1, max = 5_000) val content: String?,
        @Size(min = 1, max = 10) val tagsToAdd: List<String>?,
        @Size(min = 1, max = 10) val tagsToDelete: List<String>?,
        val isFrozen: Boolean?
    )
}