package com.example.demo.library.mysql

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import javax.sql.DataSource

@Configuration(proxyBeanMethods = false)
@Profile("stage | prod")
class ProdDataSourceConfig {
    @Bean(DataSourceName.MASTER)
    @ConfigurationProperties("spring.datasource.hikari.master")
    fun masterDataSource(): DataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    @Bean(DataSourceName.SLAVE)
    @ConfigurationProperties("spring.datasource.hikari.slave")
    fun slaveDataSource(): DataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()

    @Primary
    @Bean(name = [DataSourceName.LAZY])
    fun lazyDataSource(
        @Qualifier(DataSourceName.MASTER) master: DataSource,
        @Qualifier(DataSourceName.SLAVE) slave: DataSource?,
    ): DataSource = LazyConnectionDataSourceProxy(master).apply { setReadOnlyDataSource(slave) }
}
