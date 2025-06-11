package com.example.demo.app.api.user.inbound.restful

import com.example.demo.library.security.annotation.AuthRequired
import com.example.demo.library.security.annotation.CurrentUser
import com.example.demo.support.OnuiiUser
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthTestController {
    private val logger = KotlinLogging.logger {}

    @AuthRequired
    @GetMapping
    fun test(@CurrentUser user: OnuiiUser): Boolean {
        logger.info { "$user is logged in" }
        return true
    }
}
