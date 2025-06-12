package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

abstract class CustomException(val type: ErrorType, message: String?, cause: Throwable?) : RuntimeException(message, cause)
