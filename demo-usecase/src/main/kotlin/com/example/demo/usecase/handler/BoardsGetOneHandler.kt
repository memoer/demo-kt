package com.example.demo.usecase.handler

import com.example.demo.usecase.handler.__dto__.BoardDto
import com.example.demo.usecase.port.BoardReader
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardsGetOneHandler(private val boardReader: BoardReader) {

    fun handle(args: Args): BoardDto = boardReader.read(args.id).let { BoardDto.fromEntity(it) }

    data class Args(val id: UUID)

}