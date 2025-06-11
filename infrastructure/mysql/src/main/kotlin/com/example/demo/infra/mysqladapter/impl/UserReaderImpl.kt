package com.example.demo.infra.mysqladapter.impl

import com.example.demo.core.user.domain.User
import com.example.demo.core.user.port.UserReader
import com.example.demo.infra.mysqladapter.entity.QUserEntity
import com.example.demo.infra.mysqladapter.repository.UserSchemaRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserReaderImpl(private val repository: UserSchemaRepository, private val qf: JPAQueryFactory) : UserReader {
    override fun readById(id: Long): User? {
        val u = QUserEntity.userEntity
        val entity = qf.selectFrom(u).where(u.id.eq(id)).fetchOne()
        if (entity == null) {
            throw RuntimeException()
        }
        return entity.toDomain()
    }

    override fun readByEmail(email: String): User? = repository.findByEmail(email).orElse(null)?.toDomain()
}
