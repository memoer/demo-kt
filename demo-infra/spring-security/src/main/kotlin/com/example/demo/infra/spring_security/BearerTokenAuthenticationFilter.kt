package com.example.demo.infra.spring_security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolderStrategy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.web.filter.OncePerRequestFilter


class BearerTokenAuthenticationFilter(
    userDetailsService: UserDetailsService,
    authenticationEntryPoint: AuthenticationEntryPoint
) : OncePerRequestFilter() {
    private val strategy: SecurityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy()
    private val authenticationManager: AuthenticationManager =
        ProviderManager(listOf(JwtAuthenticationProvider(userDetailsService)))
    private val failureHandler: AuthenticationFailureHandler =
        AuthenticationEntryPointFailureHandler(authenticationEntryPoint)
    private val HEADER_NAME: String = "Authorization"
    private val AUTH_TYPE: String = "Bearer"

    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val header = request.getHeader(HEADER_NAME)
        val token = extractTokenFrom(header)

        if (token != null) {
            tryAuth(token, request, response)
        }

        filterChain.doFilter(request, response)
    }

    private fun extractTokenFrom(header: String?): String? {
        if (header == null) {
            return null
        }

        val strings: Array<String?> = header.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (strings.size != 2) {
            return null
        } else if (!strings[0].equals(AUTH_TYPE)) {
            return null
        }

        return strings[1]
    }

    private fun tryAuth(
        token: String,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Boolean {
        val authentication = JwtAuthenticationToken(AUTH_TYPE, token, null)
        val authResult: Authentication
        try {
            authResult = this.authenticationManager.authenticate(authentication)
        } catch (ex: AuthenticationException) {
            this.onUnsuccessfulAuthentication(request, response, ex)
            return true
        }

        with(strategy) {
            val ctx = createEmptyContext()
            ctx.authentication = authResult
            ctx
        }.run { strategy.context = this }
        return false
    }

    private fun onUnsuccessfulAuthentication(
        request: HttpServletRequest?, response: HttpServletResponse?, ex: AuthenticationException
    ) {
        this.strategy.clearContext()
        this.failureHandler.onAuthenticationFailure(request, response, ex)
        this.logger.debug("Failed to process authentication request", ex)
    }
}