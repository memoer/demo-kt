package com.example.demo.infra.redis.mapping

import com.example.demo.domain.BoardEntity
import com.example.demo.lib.ReflectionUtils
import java.util.UUID

data class BoardMapping(
    val id: UUID = UUID.randomUUID(),
    val title: String="",
    val content: String="",
    val tags: MutableList<String> = mutableListOf(),
    val isFrozen: Boolean = false,
) {
    companion object {
        fun fromEntity(entity: BoardEntity): BoardMapping =
            BoardMapping(entity.id, entity.title, entity.content, entity.tags, entity.isFrozen)
    }

    fun toEntity(): BoardEntity {
        val entity = BoardEntity(title, content, tags)
        ReflectionUtils.setPropertyValue(entity, "id", this.id)
        ReflectionUtils.setPropertyValue(entity, "isFrozen", this.isFrozen)
        return entity
    }
}
