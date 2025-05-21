package com.example.demo.usecase.handler.__dto__

import com.example.demo.domain.BoardEntity
import java.util.UUID

data class BoardDto(val id: UUID, val title: String, val content: String, val tags: List<String>, val isFrozen: Boolean){
    companion object{
        fun fromEntity(entity: BoardEntity): BoardDto = BoardDto(entity.id, entity.title, entity.content, entity.tags, entity.isFrozen)
    }
}