package com.example.demo.infra.redis.impl

import com.example.demo.core.board.domain.Board
import com.example.demo.core.board.port.BoardWriter
import com.example.demo.infra.redis.entity.BoardEntity
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class BoardWriterImpl(private val om: ObjectMapper, private val template: StringRedisTemplate) : BoardWriter {
    override fun write(entity: Board): Unit = BoardEntity.fromDomain(entity)
        .run { om.writeValueAsString(this) }
        .run { template.opsForValue().set(entity.id.toString(), this) }

    override fun delete(id: UUID) {
        template.opsForValue().operations.unlink(id.toString())
    }
}
