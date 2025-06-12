package com.example.demo.app.api.board.inbound.externalevent

import com.example.demo.library.kafka.DefaultKafkaListener
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.stereotype.Component

@Component
class TestKafkaConsumer {
    @DefaultKafkaListener(topics = ["test"])
    fun consume(record: ConsumerRecord<String, String>) {
        println("Consuming record: $record")
    }
}
