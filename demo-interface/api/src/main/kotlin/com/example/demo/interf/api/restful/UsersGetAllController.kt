package com.example.demo.interf.api.restful

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersGetAllController {
    @GetMapping
    fun control() {

    }
}