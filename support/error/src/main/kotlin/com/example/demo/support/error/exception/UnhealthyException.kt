package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class UnhealthyException(message: String, cause: Throwable?) : CustomException(ErrorType.UNHEALTHY, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.UNHEALTHY.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.TOO_MANY_REQUEST.name, null)
}
