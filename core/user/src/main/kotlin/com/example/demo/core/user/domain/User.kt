package com.example.demo.core.user.domain

class User {
    val id: Long = -1L
    var name: String private set
    var email: String private set
    var password: String private set

    constructor(name: String, email: String, password: String) {
        this.name = name
        this.email = email
        this.password = password
    }

    fun changeName(name: String) {
        this.name = name
    }

    fun changePassword(password: String) {
        this.password = password
    }
}
