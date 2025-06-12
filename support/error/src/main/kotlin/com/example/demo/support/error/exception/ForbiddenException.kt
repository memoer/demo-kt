package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class ForbiddenException(message: String?, cause: Throwable?) : CustomException(ErrorType.FORBIDDEN, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.FORBIDDEN.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.FORBIDDEN.name, null)
}
