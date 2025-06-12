package com.example.demo.app.api.user.inbound.restful

import com.example.demo.app.api.user.usecase.handler.UserUpdateHandler
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersUpdateController(private val userUpdateHandler: UserUpdateHandler) {
    @PatchMapping("{id}")
    fun control(@PathVariable id: Long, @Valid @RequestBody request: Request) = UserUpdateHandler.Args(id, request.name, request.password).run { userUpdateHandler.handle(this) }

    data class Request(
        @NotBlank val name: String,
        @Size(min = 10, max = 20) val password: String,
    )
}
