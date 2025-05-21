package com.example.demo.usecase.handler

import com.example.demo.usecase.port.BoardReader
import com.example.demo.usecase.port.BoardWriter
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