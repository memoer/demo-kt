package com.example.demo.infra.redisadapter.impl

import com.example.demo.core.board.domain.Board
import com.example.demo.core.board.port.BoardReader
import com.example.demo.infra.redisadapter.entity.BoardEntity
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class BoardReaderImpl(private val om: ObjectMapper, private val template: StringRedisTemplate) : BoardReader {
    override fun readById(id: UUID): Board? = template.opsForValue().get(id.toString())?.let {
        om.readValue(
            it,
            BoardEntity::class.java,
        )
    }?.toDomain()

    override fun existsById(id: UUID): Boolean = template.opsForValue().get(id.toString()) != null
}
