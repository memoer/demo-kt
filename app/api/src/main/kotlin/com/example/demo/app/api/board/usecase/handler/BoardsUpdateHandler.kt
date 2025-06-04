package com.example.demo.app.api.board.usecase.handler

import com.example.demo.app.api.board.usecase.handler.dto.BoardDto
import com.example.demo.core.board.port.BoardReader
import com.example.demo.core.board.port.BoardWriter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardsUpdateHandler(private val boardReader: BoardReader, private val boardWriter: BoardWriter) {
    fun handle(id: UUID, args: Args): BoardDto {
        val entity = boardReader.readById(id) ?: throw RuntimeException()
        with(args) {
            title?.let { entity.changeTitle(it) }
            content?.let { entity.changeContent(it) }
            tagsToAdd?.let { entity.addTags(it) }
            tagsToDelete?.let { entity.deleteTags(it) }
            isFrozen?.let { if (it) entity.freeze() }
        }
        boardWriter.write(entity)
        return BoardDto.fromDomain(entity)
    }

    data class Args(
        val title: String?,
        val content: String?,
        val tagsToAdd: List<String>?,
        val tagsToDelete: List<String>?,
        val isFrozen: Boolean?,
    )
}
