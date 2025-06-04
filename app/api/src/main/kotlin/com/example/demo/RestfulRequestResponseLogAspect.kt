package com.example.demo

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.util.ContentCachingRequestWrapper

@Aspect
@Component
class RestfulRequestResponseLogAspect {

    private val logger = KotlinLogging.logger {}
    private val type = "restful"

    @Around(
        "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)",
    )
    fun restful(joinPoint: ProceedingJoinPoint): Any? {
        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes

        takeRequestLog(attributes.request)

        val startTime = System.currentTimeMillis()
        val result = joinPoint.proceed()
        val endTime = System.currentTimeMillis()

        attributes.response?.run {
            takeResponseLog(this, endTime - startTime)
        }

        return result
    }

    private fun takeRequestLog(request: HttpServletRequest) {
        val method = request.method
        val uri = request.requestURI
        val headers = extractHeaders(request)
        val cachedRequest = request as? ContentCachingRequestWrapper ?: ContentCachingRequestWrapper(request)
        val body = String(cachedRequest.contentAsByteArray, Charsets.UTF_8)
        logger.info { "$type request $method $uri $headers $body" }
    }

    private fun takeResponseLog(response: HttpServletResponse, timeTaken: Long) {
        val status = response.status
        val headers = extractHeaders(response)
        logger.info { "$type response $status $timeTaken $headers" }
    }

    private fun extractHeaders(request: HttpServletRequest): MutableMap<String, List<String>> =
        mutableMapOf<String, List<String>>().apply {
            val names = request.headerNames.toList()
            for (name in names) {
                val values = request.getHeaders(name).toList()
                put(name, values)
            }
        }

    private fun extractHeaders(response: HttpServletResponse): MutableMap<String, List<String>> =
        mutableMapOf<String, List<String>>().apply {
            val names = response.headerNames.toList()
            for (name in names) {
                val values = response.getHeaders(name).toList()
                put(name, values)
            }
        }
}
