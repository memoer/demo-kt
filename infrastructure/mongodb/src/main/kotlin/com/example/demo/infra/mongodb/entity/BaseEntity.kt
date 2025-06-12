package com.example.demo.infra.mongodb.entity

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

abstract class BaseEntity {
    @Id
    internal var id: String? = null
    internal var createdAt: LocalDateTime = LocalDateTime.now()
    internal var updatedAt: LocalDateTime = LocalDateTime.now()
}
