package com.example.demo.interf.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("health")
    fun control(): Boolean = true
}