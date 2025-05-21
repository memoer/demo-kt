package com.example.demo.infra.redis.impl

import com.example.demo.domain.BoardEntity
import com.example.demo.infra.redis.mapping.BoardMapping
import com.example.demo.usecase.port.BoardReader
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BoardReaderImpl(private val om: ObjectMapper, private val template: StringRedisTemplate) : BoardReader {
    override fun read(id: UUID): BoardEntity =
        template.opsForValue().get(id.toString())?.let { om.readValue(it, BoardMapping::class.java) }?.toEntity()
            ?: throw RuntimeException()

    override fun existsById(id: UUID): Boolean = template.opsForValue().get(id.toString()) != null
}