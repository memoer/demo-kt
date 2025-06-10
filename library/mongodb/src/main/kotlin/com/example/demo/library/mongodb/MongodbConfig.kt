package com.example.demo.library.mongodb

import com.example.demo.library.mongo.MongodbConnectionPool
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
class MongodbConfig(private val properties: MongoProperties, private val mongodbConnectionPool: MongodbConnectionPool) {
    @Bean
    fun mongoClientSettings(): MongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(properties.uri))
        .retryWrites(true)
        .writeConcern(WriteConcern.MAJORITY)
        .readPreference(ReadPreference.secondaryPreferred())
        .applyToConnectionPoolSettings {
            it
                .minSize(mongodbConnectionPool.minSize)
                .maxSize(mongodbConnectionPool.maxSize)
                .maxConnectionLifeTime(mongodbConnectionPool.maxConnectionLifeTime.seconds, TimeUnit.SECONDS)
                .maxConnectionIdleTime(mongodbConnectionPool.maxConnectionIdleTime.seconds, TimeUnit.SECONDS)
                .maxWaitTime(mongodbConnectionPool.maxWaitTime.seconds, TimeUnit.SECONDS)
        }
        .build()

    @Bean
    fun mongoClient(settings: MongoClientSettings): MongoClient = MongoClients.create(settings)

    @Bean
    fun mongoDatabaseFactory(client: MongoClient): MongoDatabaseFactory = SimpleMongoClientDatabaseFactory(client, properties.database)
}
