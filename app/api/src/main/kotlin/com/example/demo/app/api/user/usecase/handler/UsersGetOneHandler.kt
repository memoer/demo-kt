package com.example.demo.app.api.user.usecase.handler

import com.example.demo.app.api.user.usecase.handler.dto.UserDto
import com.example.demo.core.user.port.UserReader
import org.springframework.stereotype.Service

@Service
class UsersGetOneHandler(private val userReader: UserReader) {
    fun handle(args: Args): UserDto {
        val user = userReader.readById(args.id) ?: throw RuntimeException()
        return UserDto.fromDomain(user)
    }

    data class Args(val id: Long)
}
