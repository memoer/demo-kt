package com.example.demo.app.api.user.usecase.handler.dto

import com.example.demo.core.user.domain.User
import java.time.LocalDateTime

data class UserDto(
    val id: Long?,
    val name: String,
    val email: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(domain: User): UserDto = UserDto(domain.id, domain.name, domain.email, domain.createdAt, domain.updatedAt)
    }
}
