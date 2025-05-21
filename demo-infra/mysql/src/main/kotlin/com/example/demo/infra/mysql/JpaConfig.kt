package com.example.demo.infra.mysql

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration(proxyBeanMethods = false)
@EnableJpaAuditing
class JpaConfig {
}