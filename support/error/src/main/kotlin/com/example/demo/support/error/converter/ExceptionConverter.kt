package com.example.demo.support.error.converter

import com.example.demo.support.error.exception.CustomException

interface ExceptionConverter<T> {
    fun support(throwable: Throwable): Boolean
    fun convert(ex: T): CustomException
}
