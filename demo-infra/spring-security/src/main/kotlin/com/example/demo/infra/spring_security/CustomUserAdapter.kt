package com.example.demo.infra.spring_security

import com.example.demo.lib.CustomUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class CustomUserAdapter(id: Long, name: String, type: CustomUser.Type) : UserDetails {
    private val customUser: CustomUser = CustomUser(id, name, type)

    override fun getAuthorities(): List<SimpleGrantedAuthority> =
        listOf(SimpleGrantedAuthority("ROLE_" + customUser.type.name))

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return customUser.name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}