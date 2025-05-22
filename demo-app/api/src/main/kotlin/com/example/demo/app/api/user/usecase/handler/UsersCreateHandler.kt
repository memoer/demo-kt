package com.example.demo.app.api.user.usecase.handler

import com.example.demo.core.user.domain.User
import com.example.demo.core.user.port.UserWriter
import org.springframework.stereotype.Service

@Service
class UsersCreateHandler(private val userWriter: UserWriter) {
    fun handle(args: Args) = User(args.name, args.email, args.password).run { userWriter.write(this) }

    data class Args(val name: String, val email: String, val password: String)
}
