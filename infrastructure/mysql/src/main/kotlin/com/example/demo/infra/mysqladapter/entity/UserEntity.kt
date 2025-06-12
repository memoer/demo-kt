package com.example.demo.infra.mysqladapter.entity

import com.example.demo.core.user.domain.User
import com.example.demo.support.util.ReflectionUtils
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user")
class UserEntity(
    val name: String,
    val email: String,
    val password: String,
) : BaseEntity() {
    companion object {
        fun from(user: User): UserEntity = UserEntity(user.name, user.email, user.password)
            .apply {
                this.id = user.id
                this.createdAt = user.createdAt
                this.updatedAt = user.updatedAt
            }
    }

    fun toDomain(): User {
        val domain = User(name, email, password)
        ReflectionUtils.setPropertyValue(domain, "id", this.id)
        ReflectionUtils.setPropertyValue(domain, "createdAt", this.createdAt)
        ReflectionUtils.setPropertyValue(domain, "updatedAt", this.updatedAt)
        return domain
    }
}
