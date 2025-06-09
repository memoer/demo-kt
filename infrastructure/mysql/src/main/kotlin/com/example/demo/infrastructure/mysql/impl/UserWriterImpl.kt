package com.example.demo.infrastructure.mysql.impl

import com.example.demo.core.user.domain.User
import com.example.demo.core.user.port.UserWriter
import com.example.demo.infrastructure.mysql.entity.UserEntity
import com.example.demo.infrastructure.mysql.repository.UserSchemaRepository
import org.springframework.stereotype.Repository

@Repository
class UserWriterImpl(private val repository: UserSchemaRepository) : UserWriter {

    override fun write(user: User): User = UserEntity.fromDomain(user).let { repository.save(it) }.toDomain()

    override fun delete(user: User): Unit = UserEntity.fromDomain(user).run { repository.delete(this) }
}
