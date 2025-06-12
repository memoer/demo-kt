package com.example.demo.app.api.user.usecase.handler

import com.example.demo.core.user.port.UserReader
import com.example.demo.core.user.port.UserWriter
import org.springframework.stereotype.Service

@Service
class UserUpdateHandler(private val userReader: UserReader, private val userWriter: UserWriter) {
    fun handle(args: Args) {
        val user = userReader.readById(args.id) ?: throw RuntimeException()
        user.changeName(args.name)
        user.changePassword(args.password)
        userWriter.write(user)
    }

    data class Args(val id: Long, val name: String, val password: String)
}
