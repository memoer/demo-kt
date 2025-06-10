package com.example.demo.library.security.provider

import com.example.demo.library.security.token.JwtAuthenticationToken
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService

class JwtAuthenticationProvider(private val userDetailsService: UserDetailsService) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? = authentication?.credentials?.let {
        userDetailsService.loadUserByUsername(it as String)
    }?.let {
        JwtAuthenticationToken(it, null, it.authorities)
    }

    override fun supports(authentication: Class<*>): Boolean = JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
}
