package com.example.demo.infra.redisadapter.entity

import java.time.LocalDateTime
import java.util.UUID

abstract class BaseEntity {
    internal var id: UUID? = null
    internal var createdAt: LocalDateTime = LocalDateTime.now()
    internal var updatedAt: LocalDateTime = LocalDateTime.now()
}
