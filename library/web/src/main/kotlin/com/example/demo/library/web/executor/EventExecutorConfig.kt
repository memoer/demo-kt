package com.example.demo.library.web.executor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration(proxyBeanMethods = false)
@EnableAsync
class EventExecutorConfig(private val eventExecutorProps: EventExecutorProps) {
    @Bean
    fun eventExecutor(): Executor = ThreadPoolTaskExecutor().apply {
        corePoolSize = eventExecutorProps.corePoolSize
        maxPoolSize = eventExecutorProps.maxPoolSize
        queueCapacity = eventExecutorProps.queueCapacity
        setThreadNamePrefix(ExecutorName.EVENT)
    }
}
