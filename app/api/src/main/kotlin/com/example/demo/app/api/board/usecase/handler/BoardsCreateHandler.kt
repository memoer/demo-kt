package com.example.demo.app.api.board.usecase.handler

import com.example.demo.app.api.board.usecase.handler.dto.BoardDto
import com.example.demo.core.board.domain.Board
import com.example.demo.core.board.port.BoardWriter
import org.springframework.stereotype.Service

@Service
class BoardsCreateHandler(private val boardWriter: BoardWriter) {
    fun handle(args: Args): BoardDto = Board(args.title, args.content, args.tags as MutableList<String>)
        .run {
            boardWriter.write(this)
            BoardDto.fromDomain(this)
        }

    data class Args(val title: String, val content: String, val tags: List<String>)
}
