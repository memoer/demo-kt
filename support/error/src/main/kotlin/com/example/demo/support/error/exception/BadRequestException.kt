package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class BadRequestException(message: String?, cause: Throwable?) : CustomException(ErrorType.BAD_REQUEST, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.BAD_REQUEST.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.BAD_REQUEST.name, null)
}
