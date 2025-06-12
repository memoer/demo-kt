package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class UnauthorizedException(message: String?, cause: Throwable?) : CustomException(ErrorType.UNAUTHORIZED, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.UNAUTHORIZED.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.UNAUTHORIZED.name, null)
}
