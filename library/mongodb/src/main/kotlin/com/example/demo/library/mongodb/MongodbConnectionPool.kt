package com.example.demo.library.mongo

import org.springframework.boot.context.properties.ConfigurationProperties
import java.time.Duration

@ConfigurationProperties(prefix = "spring.data.mongodb.connection-pool")
data class MongodbConnectionPool(
    internal val minSize: Int,
    internal val maxSize: Int,
    internal val maxConnectionLifeTime: Duration,
    internal val maxConnectionIdleTime: Duration,
    internal val maxWaitTime: Duration,
)
