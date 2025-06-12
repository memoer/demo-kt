package com.example.demo.infra.redisadapter.impl

import com.example.demo.core.board.domain.Board
import com.example.demo.core.board.port.BoardWriter
import com.example.demo.infra.redisadapter.entity.BoardEntity
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class BoardWriterImpl(private val om: ObjectMapper, private val template: StringRedisTemplate) : BoardWriter {
    override fun write(board: Board): Unit = BoardEntity.from(board)
        .run {
            this.id = this.id ?: UUID.randomUUID()
            val json = om.writeValueAsString(this)
            template.opsForValue().set(this.id.toString(), json)
        }

    override fun delete(id: UUID) {
        template.opsForValue().operations.unlink(id.toString())
    }
}
