package com.example.demo.support.error.resolver

import com.example.demo.support.error.ErrorType
import com.example.demo.support.error.exception.CustomException
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ErrorViewResolver {

    fun resolve(ex: CustomException): Result = Result(
        LocalDateTime.now(),
        ex.javaClass.simpleName,
        ex.type,
        ex.type.statusCode,
        ex.type.onuiiCode,
        ex.message ?: "Unknown error",
    )

    data class Result(
        val timestamp: LocalDateTime,
        val error: String,
        val type: ErrorType,
        val statusCode: Int,
        val onuiiCode: String,
        val message: String,
    ) {

        fun toMap(): Map<String, Any> = mapOf(
            "timestamp" to timestamp,
            "error" to error,
            "type" to type,
            "statusCode" to statusCode,
            "onuiiCode" to onuiiCode,
            "message" to message,
        )
    }
}
