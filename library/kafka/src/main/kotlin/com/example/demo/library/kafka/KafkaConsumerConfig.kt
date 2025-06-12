package com.example.demo.library.kafka

import com.example.demo.support.error.converter.ExceptionConverterTemplate
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.util.backoff.FixedBackOff

@Configuration(proxyBeanMethods = false)
class KafkaConsumerConfig(
    private val props: KafkaProperties,
    private val template: KafkaTemplate<String, String>,
    private val exceptionConverterTemplate: ExceptionConverterTemplate,
) {
    private val logger = KotlinLogging.logger {}

    @Value("\${spring.kafka.consumer.concurrency:3}")
    private var concurrency: Int = 3

    private fun configs() = mutableMapOf<String, Any>().apply {
        val consumer = props.consumer
        this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = consumer.bootstrapServers
        this[ConsumerConfig.GROUP_ID_CONFIG] = consumer.groupId
        this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        this[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = consumer.autoOffsetReset
        this[ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG] = false
    }

    private fun errorHandler(): DefaultErrorHandler {
        val topic = props.retry.topic
        val backOff = FixedBackOff(topic.backoff.delay.seconds, topic.attempts.toLong())
        val recoverer =
            DeadLetterPublishingRecoverer(template) { r, e ->
                exceptionConverterTemplate.run(e).run { logger.error(this) { this.message } }
                TopicPartition(r.topic() + ".dlt", r.partition())
            }
        return DefaultErrorHandler(recoverer, backOff)
    }

    @Bean
    fun myListenerContainer(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = DefaultKafkaConsumerFactory(configs())
        factory.setConcurrency(concurrency)
        factory.setCommonErrorHandler(errorHandler())
        return factory
    }
}
