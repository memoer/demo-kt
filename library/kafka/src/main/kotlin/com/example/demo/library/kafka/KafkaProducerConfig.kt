package com.example.demo.library.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@Configuration(proxyBeanMethods = false)
class KafkaProducerConfig(private val props: KafkaProperties) {
    private fun configs() = mutableMapOf<String, Any>().apply {
        val producer = props.producer
        // See https://kafka.apache.org/documentation/#producerconfigs for more properties
        this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = producer.bootstrapServers
        this[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        this[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> = KafkaTemplate(DefaultKafkaProducerFactory(configs()))
}
