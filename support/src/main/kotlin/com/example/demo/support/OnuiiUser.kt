package com.example.demo.support

data class OnuiiUser(val id: Long, val name: String, val type: Type) {

    enum class Type {
        STUDENT,
        TEACHER,
        ADMIN,
        PARENT,
    }
}
