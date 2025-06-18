package com.example.demo.library.web

import com.example.demo.library.web.annotation.ErrorMessageSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration(proxyBeanMethods = false)
class MessageConfig {
    private final val encoding: String = "UTF-8"

    @Bean
    @ErrorMessageSource
    fun errorMessageSource(): MessageSource = ReloadableResourceBundleMessageSource().apply {
        setBasename("classpath:/messages/error")
        setDefaultEncoding(encoding)
    }

    @Bean
    fun validationMessageSource(): MessageSource = ReloadableResourceBundleMessageSource().apply {
        setBasename("classpath:/messages/validation")
        setDefaultEncoding(encoding)
    }

    @Bean
    fun getValidator(@Qualifier("validationMessageSource") messageSource: MessageSource): LocalValidatorFactoryBean = LocalValidatorFactoryBean().apply {
        setValidationMessageSource(messageSource)
    }
}
