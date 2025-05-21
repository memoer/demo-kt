package com.example.demo.infra.spring_web

import com.example.demo.infra.spring_web.filter.WrappingRequestFilter
import jakarta.servlet.Filter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class FilterConfig {
    @Bean
    fun wrappingRequestFilter(): Filter = WrappingRequestFilter()
}