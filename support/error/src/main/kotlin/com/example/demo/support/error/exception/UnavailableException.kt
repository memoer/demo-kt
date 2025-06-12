package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class UnavailableException(message: String?, cause: Throwable?) : CustomException(ErrorType.UNAVAILABLE, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.UNAVAILABLE.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.TOO_MANY_REQUEST.name, null)
}
