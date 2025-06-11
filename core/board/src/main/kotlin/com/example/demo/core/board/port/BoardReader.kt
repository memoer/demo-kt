package com.example.demo.core.board.port

import com.example.demo.core.board.domain.Board
import java.util.UUID

interface BoardReader {
    fun readById(id: UUID): Board?
}
