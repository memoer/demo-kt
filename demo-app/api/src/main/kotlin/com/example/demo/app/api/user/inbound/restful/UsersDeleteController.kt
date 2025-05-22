package com.example.demo.app.api.user.inbound.restful

import com.example.demo.app.api.user.usecase.handler.UsersDeleteHandler
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersDeleteController(private val usersDeleteHandler: UsersDeleteHandler) {
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun control(@Valid @RequestBody request: Request) =
        UsersDeleteHandler.Args(request.email).run { usersDeleteHandler.handle(this) }

    data class Request(@Email val email: String)
}
