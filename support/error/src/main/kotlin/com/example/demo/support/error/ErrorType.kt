package com.example.demo.support.error

enum class ErrorType(val onuiiCode: String, val statusCode: Int) {
    BAD_REQUEST("001", 400),
    NOT_FOUND("002", 404),
    UNAUTHORIZED("003", 401),
    INTERNAL_SERVER_ERROR("004", 500),
    NOT_ALLOWED("005", 405),
    FORBIDDEN("006", 403),
    TIME_OUT("007", 504),
    UNAVAILABLE("008", 503),
    UNHEALTHY("009", 502),
    TOO_MANY_REQUEST("011", 429),
    TOKEN_EXPIRED("012", 401),
}
