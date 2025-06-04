package com.example.demo.infra.web.executor

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "server.executor.event")
data class EventExecutorProps(
    internal val corePoolSize: Int,
    internal val maxPoolSize: Int,
    internal val queueCapacity: Int,
)
