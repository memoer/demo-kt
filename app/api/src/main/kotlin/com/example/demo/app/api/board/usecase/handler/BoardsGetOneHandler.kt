package com.example.demo.app.api.board.usecase.handler

import com.example.demo.core.board.dto.BoardDto
import com.example.demo.core.board.port.BoardReader
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardsGetOneHandler(private val boardReader: BoardReader) {

    fun handle(args: Args): BoardDto = boardReader.readById(args.id)?.let { BoardDto.from(it) } ?: throw RuntimeException()

    data class Args(val id: UUID)
}
