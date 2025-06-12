package com.example.demo.library.security.errorhandling

import com.example.demo.support.error.converter.ExceptionConverter
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.exception.ForbiddenException
import jakarta.annotation.Nonnull
import org.springframework.security.authorization.AuthorizationDeniedException
import org.springframework.stereotype.Component

@Component
class AuthorizationDeniedExceptionConverter : ExceptionConverter<AuthorizationDeniedException> {
    override fun support(@Nonnull throwable: Throwable): Boolean = throwable is AuthorizationDeniedException

    override fun convert(@Nonnull ex: AuthorizationDeniedException): CustomException = ForbiddenException(ex.message, ex)
}
