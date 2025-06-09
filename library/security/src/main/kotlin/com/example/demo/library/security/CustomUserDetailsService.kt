package com.example.demo.library.security

import com.example.demo.support.CustomUser
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {
    override fun loadUserByUsername(token: String): UserDetails? = CustomUserAdapter(1L, "test-user", CustomUser.Type.STUDENT)
}
