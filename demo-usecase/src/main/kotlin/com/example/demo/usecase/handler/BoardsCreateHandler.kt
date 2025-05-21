package com.example.demo.usecase.handler

import com.example.demo.usecase.handler.__dto__.BoardDto
import com.example.demo.usecase.port.BoardWriter
import com.example.demo.domain.BoardEntity
import org.springframework.stereotype.Service

@Service
class BoardsCreateHandler(private val boardWriter: BoardWriter) {
    fun handle(args: Args): BoardDto = BoardEntity(args.title, args.content, args.tags as MutableList<String>)
        .run {
            boardWriter.write(this)
            BoardDto.fromEntity(this)
        }

    data class Args(val title: String, val content: String, val tags: List<String>)
}