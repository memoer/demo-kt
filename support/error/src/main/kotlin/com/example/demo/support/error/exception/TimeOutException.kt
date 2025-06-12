package com.example.demo.support.error.exception

import com.example.demo.support.error.ErrorType

class TimeOutException(message: String?, cause: Throwable?) : CustomException(ErrorType.TIME_OUT, message, cause) {
    constructor(cause: Throwable) : this(ErrorType.TIME_OUT.name, cause)
    constructor(message: String) : this(message, null)
    constructor() : this(ErrorType.TIME_OUT.name, null)
}
