package com.example.demo.library.web.annotation

import org.springframework.beans.factory.annotation.Qualifier

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Qualifier("errorMessageSource")
annotation class ErrorMessageSource
