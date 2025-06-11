package com.example.demo.library.security.service

import com.example.demo.client.auth.AuthClient
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val authClient: AuthClient) : UserDetailsService {
    override fun loadUserByUsername(token: String): UserDetails = authClient.verify(token).run {
        OnuiiUserAdapter(id.toLong(), name, type)
    }
}
