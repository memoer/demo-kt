package com.example.demo.library.web.error

import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties
import org.springframework.boot.autoconfigure.web.ErrorProperties
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class CustomBasicErrorController(
    @Autowired(required = false) private val graphqlProps: GraphQlProperties,
    errorAttributes: ErrorAttributes,
    errorProperties: ErrorProperties,
) : BasicErrorController(errorAttributes, errorProperties) {
    override fun getStatus(request: HttpServletRequest): HttpStatus? {
        if (isGraphqlRequest(request)) {
            return HttpStatus.OK
        }
        return super.getStatus(request)
    }

    private fun isGraphqlRequest(request: HttpServletRequest): Boolean {
        if (request.method != "POST") {
            return false
        }
        val requestUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI) as String
        return requestUri.contains(this.graphqlProps.path)
    }
}
