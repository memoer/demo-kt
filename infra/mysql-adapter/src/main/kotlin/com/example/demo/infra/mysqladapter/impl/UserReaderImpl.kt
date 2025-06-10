package com.example.demo.infra.mysqladapter.impl

import com.example.demo.core.user.domain.User
import com.example.demo.core.user.port.UserReader
import com.example.demo.infra.mysqladapter.repository.UserSchemaRepository
import org.springframework.stereotype.Repository

@Repository
class UserReaderImpl(private val repository: UserSchemaRepository) : UserReader {
    override fun readById(id: Long): User? = repository.findById(id).orElse(null)?.toDomain()
    override fun readByEmail(email: String): User? = repository.findByEmail(email).orElse(null)?.toDomain()
}
