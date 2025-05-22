package com.example.demo.core.user.port

import com.example.demo.core.user.domain.User

interface UserReader {
    fun readById(id: Long): User?
    fun readByEmail(email: String): User?
}
