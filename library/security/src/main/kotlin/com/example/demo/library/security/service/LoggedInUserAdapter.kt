package com.example.demo.library.security.service

import com.example.demo.support.LoggedInUser
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoggedInUserAdapter(id: Long, name: String, type: LoggedInUser.Type) : UserDetails {
    val user: LoggedInUser = LoggedInUser(id, name, type)

    override fun getAuthorities(): List<SimpleGrantedAuthority> = listOf(SimpleGrantedAuthority("ROLE_" + user.type.name))

    override fun getPassword(): String? = null

    override fun getUsername(): String = user.name

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
