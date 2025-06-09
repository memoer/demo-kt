package com.example.demo.library.security

import com.example.demo.support.CustomUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserAdapter(id: Long, name: String, type: CustomUser.Type) : UserDetails {
    private val customUser: CustomUser = CustomUser(id, name, type)

    override fun getAuthorities(): List<SimpleGrantedAuthority> = listOf(SimpleGrantedAuthority("ROLE_" + customUser.type.name))

    override fun getPassword(): String? = null

    override fun getUsername(): String = customUser.name

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
