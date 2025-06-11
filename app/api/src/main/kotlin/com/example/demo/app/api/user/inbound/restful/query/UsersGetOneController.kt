package com.example.demo.app.api.user.inbound.restful.query

import com.example.demo.app.api.user.usecase.handler.UsersGetOneHandler
import com.example.demo.app.api.user.usecase.handler.dto.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersGetOneController(private val usersGetOneHandler: UsersGetOneHandler) {
    @GetMapping("{id}")
    fun control(@PathVariable id: Long): UserDto = UsersGetOneHandler.Args(id).run { usersGetOneHandler.handle(this) }
}
