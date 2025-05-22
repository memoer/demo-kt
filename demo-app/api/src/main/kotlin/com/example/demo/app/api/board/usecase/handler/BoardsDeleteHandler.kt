package com.example.demo.app.api.board.usecase.handler

import com.example.demo.core.board.port.BoardReader
import com.example.demo.core.board.port.BoardWriter
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardsDeleteHandler(private val boardReader: BoardReader, private val boardWriter: BoardWriter) {

    fun handle(id: UUID) {
        val existsById = boardReader.existsById(id)
        if (existsById) {
            throw RuntimeException("that id already exists.")
        }
        boardWriter.delete(id)
    }
}
