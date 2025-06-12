package com.example.demo.library.security

import com.example.demo.library.security.filter.BearerTokenAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration(proxyBeanMethods = false)
@EnableMethodSecurity
class SecurityConfig(
    private val http: HttpSecurity,
    private val props: SecurityProps,
    private val userDetailsService: UserDetailsService,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(): SecurityFilterChain? = http
        .cors(cors())
        .csrf { it.disable() }
        .requestCache { it.disable() }
        .formLogin { it.disable() }
        .httpBasic { it.disable() }
        .logout { it.disable() }
        .anonymous { it.disable() }
        .sessionManagement { it.disable() }
        .exceptionHandling(exceptionHandling())
        .authorizeHttpRequests(authorizeHttpRequestsCustomizer())
        .addFilterBefore(
            BearerTokenAuthenticationFilter(userDetailsService, authenticationEntryPoint),
            SecurityContextHolderAwareRequestFilter::class.java,
        )
        .build()

    private fun cors(): Customizer<CorsConfigurer<HttpSecurity>> {
        val config = CorsConfiguration()
            .apply {
                allowedOrigins = props.cors.allowedOrigins
                allowedMethods = props.cors.allowedMethods
                allowCredentials = props.cors.allowCredentials
                allowedHeaders = props.cors.allowedHeaders
            }
        val source = UrlBasedCorsConfigurationSource().apply { registerCorsConfiguration("/**", config) }
        return Customizer { it.configurationSource(source) }
    }

    private fun exceptionHandling(): Customizer<ExceptionHandlingConfigurer<HttpSecurity>> = Customizer {
        it.authenticationEntryPoint(authenticationEntryPoint)
        it.accessDeniedHandler(accessDeniedHandler)
    }

    private fun authorizeHttpRequestsCustomizer(): Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> = Customizer {
        it.requestMatchers("/observer/prometheus").permitAll()
            .requestMatchers("/observer/**").hasRole("ADMIN")
            .anyRequest().permitAll()
    }
}
