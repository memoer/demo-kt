package com.example.demo.graphql.error

import com.example.demo.support.error.converter.ExceptionConverterTemplate
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.resolver.ErrorViewResolver
import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.Nonnull
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class GraphqlErrorHandler(
    private val exceptionConverterTemplate: ExceptionConverterTemplate,
    private val errorViewResolver: ErrorViewResolver,
) : DataFetcherExceptionResolverAdapter() {
    private val log = KotlinLogging.logger {}

    override fun resolveToSingleError(
        @Nonnull throwable: Throwable,
        @Nonnull env: DataFetchingEnvironment,
    ): GraphQLError {
        val ex: CustomException = exceptionConverterTemplate.convert(throwable)
        log.error(ex) { ex.message }
        return GraphqlErrorBuilder.newError()
            .message(ex.message)
            .errorType(convert(ex.type))
            .extensions(errorViewResolver.resolve(ex).toMap())
            .build()
    }

    private fun convert(@Nonnull type: com.example.demo.support.error.ErrorType): ErrorType = when (type) {
        com.example.demo.support.error.ErrorType.BAD_REQUEST -> ErrorType.BAD_REQUEST
        com.example.demo.support.error.ErrorType.NOT_FOUND -> ErrorType.NOT_FOUND
        com.example.demo.support.error.ErrorType.UNAUTHORIZED -> ErrorType.UNAUTHORIZED
        com.example.demo.support.error.ErrorType.FORBIDDEN -> ErrorType.FORBIDDEN
        else -> ErrorType.INTERNAL_ERROR
    }
}
