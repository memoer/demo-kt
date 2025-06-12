package com.example.demo.support.error.converter

import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.exception.InternalServerErrorException
import org.springframework.stereotype.Component

@Component
class ExceptionConverterTemplate(private val exceptionConverters: List<ExceptionConverter<Any>>) {

    fun run(throwable: Throwable): CustomException {
        if (throwable is CustomException) {
            return throwable
        } else {
            for (exceptionConverter in exceptionConverters) {
                if (!exceptionConverter.support(throwable)) {
                    continue
                }
                // noinspection unchecked
                return exceptionConverter.convert(throwable)
            }
            return InternalServerErrorException(throwable)
        }
    }
}
