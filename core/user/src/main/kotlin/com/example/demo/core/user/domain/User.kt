package com.example.demo.core.user.domain

import com.example.demo.core.common.domain.Base
import java.time.LocalDateTime

class User : Base<Long> {
    var name: String private set
    var email: String private set
    var password: String private set

    constructor(name: String, email: String, password: String) : super() {
        this.name = name
        this.email = email
        this.password = password
    }

    fun changeName(name: String) {
        this.name = name
        this.updatedAt = LocalDateTime.now()
    }

    fun changePassword(password: String) {
        this.password = password
        this.updatedAt = LocalDateTime.now()
    }
}
