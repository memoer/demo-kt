package com.example.demo.library.security.error

import com.example.demo.support.error.converter.ExceptionConverter
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.exception.UnauthorizedException
import jakarta.annotation.Nonnull
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.stereotype.Component

@Component
class AuthenticationCredentialsNotFoundExceptionConverter : ExceptionConverter<AuthenticationCredentialsNotFoundException> {
    override fun support(@Nonnull throwable: Throwable): Boolean = throwable is AuthenticationCredentialsNotFoundException

    override fun convert(@Nonnull ex: AuthenticationCredentialsNotFoundException): CustomException = UnauthorizedException(ex.message, ex)
}
