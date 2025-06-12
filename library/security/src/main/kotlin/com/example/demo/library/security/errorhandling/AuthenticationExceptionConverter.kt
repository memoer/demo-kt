package com.example.demo.library.security.errorhandling

import com.example.demo.support.error.converter.ExceptionConverter
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.exception.UnauthorizedException
import jakarta.annotation.Nonnull
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component

@Component
class AuthenticationExceptionConverter : ExceptionConverter<AuthenticationException> {
    override fun support(@Nonnull throwable: Throwable): Boolean = throwable is AuthenticationException

    override fun convert(@Nonnull ex: AuthenticationException): CustomException = UnauthorizedException(ex.message, ex)
}
