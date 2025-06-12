package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class NotAllowedException(message: String?, cause: Throwable?) : CustomException(ErrorType.NOT_ALLOWED, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.NOT_ALLOWED.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.NOT_ALLOWED.name, null)
}
