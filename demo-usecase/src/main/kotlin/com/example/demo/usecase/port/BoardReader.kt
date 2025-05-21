package com.example.demo.usecase.port

import com.example.demo.domain.BoardEntity
import java.util.UUID

interface BoardReader {
    fun read(id: UUID): BoardEntity
    fun existsById(id: UUID): Boolean
}