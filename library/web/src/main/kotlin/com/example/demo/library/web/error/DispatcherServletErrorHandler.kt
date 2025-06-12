package com.example.demo.library.web.error

import com.example.demo.support.error.converter.ExceptionConverterTemplate
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.resolver.ErrorViewResolver
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class DispatcherServletErrorHandler(
    private val exceptionConverterTemplate: ExceptionConverterTemplate,
    private val errorViewResolver: ErrorViewResolver,
) {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler
    fun handle(throwable: Throwable?): ErrorViewResolver.Result {
        val ex: CustomException = exceptionConverterTemplate.run(throwable!!)
        logger.error(ex) { ex.message }
        return errorViewResolver.resolve(ex)
    }
}
