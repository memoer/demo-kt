package com.example.demo.support.error.converter

import com.example.demo.support.error.exception.CustomException
import org.springframework.stereotype.Component

@Component
class CustomExceptionExceptionConverter : ExceptionConverter<CustomException> {
    override fun support(throwable: Throwable): Boolean = throwable is CustomException

    override fun convert(ex: CustomException): CustomException = ex
}
