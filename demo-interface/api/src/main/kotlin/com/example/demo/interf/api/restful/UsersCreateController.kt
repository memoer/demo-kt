package com.example.demo.interf.api.restful

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersCreateController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun control(@Valid @RequestBody request: Request) {

    }

    data class Request(@NotBlank val name: String, @Email val email: String, @Size(min = 10, max = 20) val password: String)
}