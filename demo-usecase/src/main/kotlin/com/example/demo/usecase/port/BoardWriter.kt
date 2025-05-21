package com.example.demo.usecase.port

import com.example.demo.domain.BoardEntity
import java.util.UUID

interface BoardWriter {
    fun write(entity: BoardEntity): Unit
    fun delete(id: UUID): Unit
}