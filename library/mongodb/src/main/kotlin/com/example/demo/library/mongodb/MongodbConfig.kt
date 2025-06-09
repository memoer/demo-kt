package com.example.demo.library.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ReadPreference
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import java.util.concurrent.TimeUnit

@Configuration(proxyBeanMethods = false)
class MongodbConfig(private val properties: MongoProperties, private val mongoConnectionPool: MongoConnectionPool) {
    @Bean
    fun mongoClientSettings(): MongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(properties.uri))
        .retryWrites(true)
        .writeConcern(WriteConcern.MAJORITY)
        .readPreference(ReadPreference.secondaryPreferred())
        .applyToConnectionPoolSettings {
            it
                .minSize(mongoConnectionPool.minSize)
                .maxSize(mongoConnectionPool.maxSize)
                .maxConnectionLifeTime(mongoConnectionPool.maxConnectionLifeTime.seconds, TimeUnit.SECONDS)
                .maxConnectionIdleTime(mongoConnectionPool.maxConnectionIdleTime.seconds, TimeUnit.SECONDS)
                .maxWaitTime(mongoConnectionPool.maxWaitTime.seconds, TimeUnit.SECONDS)
        }
        .build()

    @Bean
    fun mongoClient(settings: MongoClientSettings): MongoClient = MongoClients.create(settings)

    @Bean
    fun mongoDatabaseFactory(client: MongoClient): MongoDatabaseFactory = SimpleMongoClientDatabaseFactory(client, properties.database)
}
