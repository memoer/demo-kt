package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class NotFoundException(message: String?, cause: Throwable?) : CustomException(ErrorType.NOT_FOUND, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.NOT_FOUND.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.NOT_FOUND.name, null)
}
