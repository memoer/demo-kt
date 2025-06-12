package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class TooManyRequestException(message: String?, cause: Throwable?) : CustomException(ErrorType.TOO_MANY_REQUEST, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.TOO_MANY_REQUEST.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.TOO_MANY_REQUEST.name, null)
}
