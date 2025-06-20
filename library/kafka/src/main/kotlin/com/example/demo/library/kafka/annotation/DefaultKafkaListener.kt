package com.example.demo.library.kafka.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.kafka.annotation.KafkaListener

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@KafkaListener
annotation class DefaultKafkaListener(
    @get:AliasFor(annotation = KafkaListener::class, attribute = "topics") val topics: Array<String>,
    @get:AliasFor(
        annotation = KafkaListener::class,
        attribute = "containerFactory",
    ) val containerFactory: String = "myListenerContainer",
)
