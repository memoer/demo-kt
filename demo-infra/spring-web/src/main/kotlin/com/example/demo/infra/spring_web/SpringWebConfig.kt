package com.example.demo.infra.spring_web

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration


@Configuration(proxyBeanMethods = false)
class SpringWebConfig {
    @Bean
    // https://pompitzz.github.io/blog/Spring/RestTemplate_Timeout.html#connectiontimeout%E1%84%8B%E1%85%B3%E1%86%AB-socket-connect%E1%84%8B%E1%85%A6%E1%84%89%E1%85%A5-%E1%84%89%E1%85%A1%E1%84%8B%E1%85%AD%E1%86%BC%E1%84%83%E1%85%AC%E1%86%AB%E1%84%83%E1%85%A1
    fun restTemplate(): RestTemplate = RestTemplateBuilder()
        .apply { connectTimeout(Duration.ofMillis(1_000)) }
        .build()
}