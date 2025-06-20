package com.example.demo.support.error.resolver

import com.example.demo.support.error.ErrorType
import com.example.demo.support.error.exception.CustomException
import io.github.oshai.kotlinlogging.KLogger

class ErrorLogResolver {

    fun resolve(logger: KLogger, t: Throwable, m: MutableMap<String, Any>?) {
        resolveInternal(logger, t, m ?: mutableMapOf(), false)
    }

    fun resolveError(logger: KLogger, t: Throwable, m: MutableMap<String, Any>?) {
        resolveInternal(logger, t, m ?: mutableMapOf(), true)
    }

    private fun resolveInternal(logger: KLogger, t: Throwable, m: MutableMap<String, Any>, forceError: Boolean) {
        val map = insertCodeToMap(t, m)

        if (forceError) {
            logger.error(appendEntries(map), t.message, t)
            return
        }

        if (isError(t)) {
            logger.error(appendEntries(map), t.message, t)
        } else {
            logger.warn(appendEntries(map), t.message, t)
        }
    }

    private fun insertCodeToMap(t: Throwable, m: MutableMap<String, Any>): MutableMap<String, Any> {
        if (!isCustomException(t)) {
            val type: ErrorType = ErrorType.INTERNAL_SERVER_ERROR
            m.put("status_code", type.statusCode)
            m.put("onuii_code", type.onuiiCode)
        } else {
            val ex: CustomException = t as CustomException
            val type: ErrorType = ex.type
            m.put("status_code", type.statusCode)
            m.put("onuii_code", type.onuiiCode)
        }
        return m
    }

    private fun isError(t: Throwable): Boolean {
        if (!isCustomException(t)) {
            return true
        }

        val ex: CustomException = t as CustomException
        return when (ex.type) {
            ErrorType.BAD_REQUEST, ErrorType.NOT_FOUND, ErrorType.UNAUTHORIZED, ErrorType.NOT_ALLOWED, ErrorType.FORBIDDEN, ErrorType.TOO_MANY_REQUEST, ErrorType.TOKEN_EXPIRED -> false
            else -> true
        }
    }

    private fun isCustomException(t: Throwable): Boolean = t is CustomException
}
