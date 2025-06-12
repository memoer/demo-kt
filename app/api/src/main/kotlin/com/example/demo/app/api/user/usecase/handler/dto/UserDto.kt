package com.example.demo.app.api.user.usecase.handler.dto

import com.example.demo.core.user.domain.User

data class UserDto(val id: Long?, val name: String, val email: String) {
    companion object {
        fun from(domain: User): UserDto = UserDto(domain.id, domain.name, domain.email)
    }
}
