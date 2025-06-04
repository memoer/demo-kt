package com.example.demo.infra.redis

import io.lettuce.core.ClientOptions
import io.lettuce.core.ReadFrom
import io.lettuce.core.SocketOptions
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import java.time.Duration

@Configuration(proxyBeanMethods = false)
@Profile("stage | prod")
class ProdRedisFactoryConfig {
    @Value("\${spring.data.redis.master.host}")
    private lateinit var masterHost: String

    @Value("\${spring.data.redis.slave.host}")
    private lateinit var slaveHost: String

    @Value("\${spring.data.redis.master.port}")
    private var masterPort: Int? = null

    @Value("\${spring.data.redis.slave.port}")
    private var slavePort: Int? = null

    private fun masterReplicaConfig(): RedisStaticMasterReplicaConfiguration =
        RedisStaticMasterReplicaConfiguration(masterHost, masterPort ?: 6379)
            .apply { addNode(slaveHost, slavePort ?: 6379) }

    private fun lettuceConnConfig(): LettuceClientConfiguration =
        run {
            SocketOptions.builder()
                .connectTimeout(Duration.ofMillis(1_500))
                .keepAlive(true)
                .build()
        }.let {
            ClientOptions.builder().socketOptions(it).pingBeforeActivateConnection(true).autoReconnect(true).build()
        }.let {
            LettuceClientConfiguration.builder()
                .clientOptions(it)
                .commandTimeout(Duration.ofMillis(1_000))
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build()
        }

    @Bean
    @Qualifier
    fun redisConnConfig(): RedisConnectionFactory = LettuceConnectionFactory(masterReplicaConfig(), lettuceConnConfig())
        .apply { connection.select(1) }
}
