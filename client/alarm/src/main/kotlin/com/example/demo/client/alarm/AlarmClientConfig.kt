package com.example.demo.client.alarm

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.grpc.client.GrpcChannelFactory

@Configuration(proxyBeanMethods = false)
class AlarmClientConfig {
    @Bean
    fun alarmStub(channels: GrpcChannelFactory): AlarmServiceGrpc.AlarmServiceBlockingStub = AlarmServiceGrpc.newBlockingStub(channels.createChannel("alarm"))
}
