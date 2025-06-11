package com.example.demo.app.api.board.usecase.handler

import com.example.demo.core.board.port.BoardWriter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardsDeleteHandler(private val boardWriter: BoardWriter) {

    fun handle(id: UUID) {
        boardWriter.delete(id)
    }
}
