package com.example.demo.infra.mysqladapter.repository

import com.example.demo.infra.mysqladapter.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserSchemaRepository : JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where u.email = ?1")
    fun findByEmail(email: String): Optional<UserEntity>
}
