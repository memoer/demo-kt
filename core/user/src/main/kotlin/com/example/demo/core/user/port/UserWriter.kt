package com.example.demo.core.user.port

import com.example.demo.core.user.domain.User

interface UserWriter {
    fun write(user: User): User
    fun delete(user: User)
}
