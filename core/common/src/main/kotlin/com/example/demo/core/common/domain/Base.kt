package com.example.demo.core.common.domain

import java.time.LocalDateTime

abstract class Base<T> {
    var id: T?
        private set
    var createdAt: LocalDateTime
        private set
    var updatedAt: LocalDateTime
        protected set

    constructor(id: T? = null) {
        this.id = id
        this.createdAt = LocalDateTime.now()
        this.updatedAt = LocalDateTime.now()
    }
}
