package com.example.demo.infra.mysqladapter.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    internal var id: Long? = null
    internal var createdAt: LocalDateTime = LocalDateTime.now()
    internal var updatedAt: LocalDateTime = LocalDateTime.now()
}
