package com.example.demo.lib

data class CustomUser(val id: Long, val name: String, val type: Type) {

    enum class Type {
        STUDENT, TEACHER, ADMIN, PARENT
    }
}