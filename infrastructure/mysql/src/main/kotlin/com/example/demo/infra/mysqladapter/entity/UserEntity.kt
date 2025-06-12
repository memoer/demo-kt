package com.example.demo.infra.mysqladapter.entity

import com.example.demo.core.user.domain.User
import com.example.demo.support.util.ReflectionUtils
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: String,
    val email: String,
    val password: String,
) {
    companion object {
        fun from(user: User): UserEntity = UserEntity(user.id, user.name, user.email, user.password)
    }

    fun toDomain(): User = User(name, email, password).apply {
        ReflectionUtils.setPropertyValue(this, "id", this@UserEntity.id)
    }
}
