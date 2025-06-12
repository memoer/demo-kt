package com.example.demo.support.error.resolver

import com.example.demo.support.error.exception.CustomException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ErrorViewResolver {
    fun response(ex: CustomException): MutableMap<String, Any> = mutableMapOf<String, Any>().apply {
        this["timestamp"] = LocalDateTime.now()
        this["error"] = ex.javaClass.simpleName
        this["type"] = ex.type
        this["statusCode"] = ex.type.statusCode
        this["onuiiCode"] = ex.type.onuiiCode
        this["message"] = ex.message ?: "Unknown error"
    }
}
