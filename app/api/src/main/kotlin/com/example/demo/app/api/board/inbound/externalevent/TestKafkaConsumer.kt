package com.example.demo.app.api.board.inbound.externalevent

import com.example.demo.library.kafka.annotation.DefaultKafkaListener
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.stereotype.Component

@Component
class TestKafkaConsumer {
    @DefaultKafkaListener(topics = ["test"])
    fun consume(record: ConsumerRecord<String, String>): Unit = throw RuntimeException("test consume")
}
