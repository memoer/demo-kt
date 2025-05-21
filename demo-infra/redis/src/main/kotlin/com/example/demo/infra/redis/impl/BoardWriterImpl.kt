package com.example.demo.infra.redis.impl

import com.example.demo.domain.BoardEntity
import com.example.demo.infra.redis.mapping.BoardMapping
import com.example.demo.usecase.port.BoardWriter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardWriterImpl(private val om: ObjectMapper, private val template: StringRedisTemplate) : BoardWriter {
    override fun write(entity: BoardEntity): Unit = BoardMapping.fromEntity(entity)
        .run { om.writeValueAsString(this) }
        .run { template.opsForValue().set(entity.id.toString(), this) }

    override fun delete(id: UUID) {
        template.opsForValue().operations.unlink(id.toString())
    }
}