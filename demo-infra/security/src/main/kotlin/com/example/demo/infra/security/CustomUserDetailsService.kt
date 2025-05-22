package com.example.demo.infra.security

import com.example.demo.lib.CustomUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(token: String): UserDetails? {
        return CustomUserAdapter(1L, "test-user", CustomUser.Type.STUDENT)
    }
}
