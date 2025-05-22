package com.example.demo.infra.mysql.entity

import com.example.demo.core.user.domain.User
import com.example.demo.lib.ReflectionUtils
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
    internal val id: Long? = null,
    internal val name: String,
    internal val email: String,
    internal val password: String,
) {
    companion object {
        fun fromDomain(user: User): UserEntity =
            UserEntity(if (user.id == -1L) null else user.id, user.name, user.email, user.password)
    }

    fun toDomain(): User = User(name, email, password).apply {
        ReflectionUtils.setPropertyValue(this, "id", this@UserEntity.id ?: -1L)
    }
}
