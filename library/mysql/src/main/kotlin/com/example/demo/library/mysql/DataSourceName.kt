package com.example.demo.library.mysql

interface DataSourceName {
    companion object {
        const val MASTER: String = "MASTER_DATA_SOURCE"

        const val SLAVE: String = "SLAVE_DATA_SOURCE"

        const val LAZY: String = "LAZY_DATA_SOURCE"
    }
}
