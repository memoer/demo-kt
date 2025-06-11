package com.example.demo.graphql

import graphql.scalars.ExtendedScalars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration(proxyBeanMethods = false)
class GraphqlConfig {
    @Bean
    fun runtimeWiringConfigurer(): RuntimeWiringConfigurer = RuntimeWiringConfigurer {
        it.scalar(ExtendedScalars.DateTime)
            .scalar(ExtendedScalars.Time)
            .scalar(ExtendedScalars.GraphQLLong)
            .scalar(ExtendedScalars.Json)
    }
}
