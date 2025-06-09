package com.example.demo.library.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class JwtAuthenticationToken(principal: Any, credentials: Any?, authorities: MutableCollection<out GrantedAuthority>?) : UsernamePasswordAuthenticationToken(principal, credentials, authorities)
