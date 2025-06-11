package com.example.demo.core.board.port

import com.example.demo.core.board.domain.Board
import java.util.UUID

interface BoardWriter {
    fun write(board: Board)
    fun delete(id: UUID)
}
