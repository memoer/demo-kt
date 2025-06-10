package com.example.demo.client.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.grpc.client.GrpcChannelFactory

@Configuration(proxyBeanMethods = false)
class AuthClientConfig {
    @Bean
    fun authStub(channels: GrpcChannelFactory): AuthServiceGrpc.AuthServiceBlockingStub = AuthServiceGrpc.newBlockingStub(channels.createChannel("auth"))
}
