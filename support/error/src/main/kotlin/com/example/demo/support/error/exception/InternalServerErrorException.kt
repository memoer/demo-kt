package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class InternalServerErrorException(message: String?, cause: Throwable?) : CustomException(ErrorType.INTERNAL_SERVER_ERROR, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.INTERNAL_SERVER_ERROR.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.INTERNAL_SERVER_ERROR.name, null)
}
