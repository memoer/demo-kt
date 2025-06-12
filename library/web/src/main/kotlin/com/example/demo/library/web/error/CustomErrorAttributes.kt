package com.example.demo.library.web.error

import com.example.demo.support.error.converter.ExceptionConverterTemplate
import com.example.demo.support.error.exception.CustomException
import com.example.demo.support.error.resolver.ErrorViewResolver
import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
class CustomErrorAttributes(
    private val exceptionConverterTemplate: ExceptionConverterTemplate,
    private val errorViewResolver: ErrorViewResolver,
    private val om: ObjectMapper,
) : DefaultErrorAttributes() {
    private val logger = KotlinLogging.logger {}

    /**
     * 톰캣에서 에러가 발생하면 톰캣이 이 에러를 다시 받고, BasicErrorController 넘깁니다. HTTP 요청의 produces값이
     * `text/html`이라면 `errorHtml()`를 호출하지만, 아니라면 `error()`를 호출합니다. 서버를 단순히 API 서버로만 사용한다면,
     * `error()`가 호출될겁니다. 두 메소드 모두 `getErrorAttributes()`를 이용하여 데이터를 반환합니다.
     */
    override fun getErrorAttributes(webRequest: WebRequest?, options: ErrorAttributeOptions?): Map<String, Any> {
        val throwable = this.getError(webRequest)
        val ex: CustomException = exceptionConverterTemplate.run(throwable)
        logger.error(ex) { ex.message }
        return errorViewResolver.resolve(ex).toMap()
    }
}
