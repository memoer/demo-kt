package com.example.demo.library.web.error

import com.example.demo.support.error.converter.ExceptionConverter
import com.example.demo.support.error.exception.BadRequestException
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.exception.ForbiddenException
import com.example.demo.support.error.exception.InternalServerErrorException
import com.example.demo.support.error.exception.NotAllowedException
import com.example.demo.support.error.exception.NotFoundException
import com.example.demo.support.error.exception.TimeOutException
import com.example.demo.support.error.exception.TooManyRequestException
import com.example.demo.support.error.exception.UnauthorizedException
import com.example.demo.support.error.exception.UnavailableException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.ErrorResponse

@Component
class ErrorResponseExceptionConverter : ExceptionConverter<ErrorResponse> {
    override fun support(throwable: Throwable): Boolean = throwable is ErrorResponse

    override fun convert(ex: ErrorResponse): CustomException {
        val statusCode: HttpStatusCode = ex.statusCode
        val converted = ex as Exception

        return when (statusCode) {
            HttpStatus.BAD_REQUEST -> BadRequestException(converted.message, converted)
            HttpStatus.UNAUTHORIZED -> UnauthorizedException(converted.message, converted)
            HttpStatus.FORBIDDEN -> ForbiddenException(converted.message, converted)
            HttpStatus.NOT_FOUND -> NotFoundException(converted.message, converted)
            HttpStatus.METHOD_NOT_ALLOWED -> NotAllowedException(converted.message, converted)
            HttpStatus.TOO_MANY_REQUESTS -> TooManyRequestException(converted.message, converted)
            HttpStatus.SERVICE_UNAVAILABLE -> UnavailableException(converted.message, converted)
            HttpStatus.GATEWAY_TIMEOUT -> TimeOutException(converted.message, converted)
            else -> InternalServerErrorException(converted.message, converted)
        }
    }
}
