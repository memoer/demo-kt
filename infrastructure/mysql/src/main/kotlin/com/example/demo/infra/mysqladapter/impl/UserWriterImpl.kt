package com.example.demo.infra.mysqladapter.impl

import com.example.demo.core.user.domain.User
import com.example.demo.core.user.port.UserWriter
import com.example.demo.infra.mysqladapter.entity.UserEntity
import com.example.demo.infra.mysqladapter.repository.UserSchemaRepository
import com.example.demo.support.util.ReflectionUtils
import org.springframework.stereotype.Repository

@Repository
class UserWriterImpl(private val repository: UserSchemaRepository) : UserWriter {

    override fun write(user: User) {
        val saved = UserEntity.fromDomain(user).run { repository.save(this) }
        ReflectionUtils.setPropertyValue(user, "id", saved.id)
    }

    override fun delete(user: User): Unit = UserEntity.fromDomain(user).run { repository.delete(this) }
}
