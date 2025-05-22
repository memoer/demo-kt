package com.example.demo.app.api.user.usecase.handler

import com.example.demo.core.user.port.UserReader
import com.example.demo.core.user.port.UserWriter
import org.springframework.stereotype.Service

@Service
class UsersDeleteHandler(private val userReader: UserReader, private val userWriter: UserWriter) {
    fun handle(args: Args) {
        val user = userReader.readByEmail(args.email) ?: throw RuntimeException()
        userWriter.delete(user)
    }

    data class Args(val email: String)
}
