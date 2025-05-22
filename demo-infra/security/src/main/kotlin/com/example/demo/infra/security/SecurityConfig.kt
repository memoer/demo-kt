package com.example.demo.infra.security

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
    fun filterChain(): SecurityFilterChain? {
        val bearerTokenAuthenticationFilter = BearerTokenAuthenticationFilter(userDetailsService, authenticationEntryPoint)
        return http.cors(cors())
            .csrf(Customizer { obj: CsrfConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .requestCache(Customizer { obj: RequestCacheConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .formLogin(Customizer { obj: FormLoginConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .httpBasic(Customizer { obj: HttpBasicConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .logout(Customizer { obj: LogoutConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .anonymous(Customizer { obj: AnonymousConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .sessionManagement(Customizer { obj: SessionManagementConfigurer<HttpSecurity?>? -> obj!!.disable() })
            .exceptionHandling(exceptionHandling())
//            .authorizeHttpRequests(authRouth())
            .addFilterBefore(bearerTokenAuthenticationFilter, SecurityContextHolderAwareRequestFilter::class.java)
            .build()
    }

    private fun cors(): Customizer<CorsConfigurer<HttpSecurity>> {
        val config = CorsConfiguration()
            .apply {
                allowedOrigins = props.cors.allowedOrigins
                allowedMethods = props.cors.allowedMethods
                allowCredentials = props.cors.allowCredentials
                allowedHeaders = props.cors.allowedHeaders
            }
        val source = UrlBasedCorsConfigurationSource().apply { registerCorsConfiguration("/&&", config) }
        return Customizer { it.configurationSource(source) }
    }

    private fun exceptionHandling(): Customizer<ExceptionHandlingConfigurer<HttpSecurity>> = Customizer {
        it.authenticationEntryPoint(authenticationEntryPoint)
        it.accessDeniedHandler(accessDeniedHandler)
    }

    private fun authRouth(): Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> =
        Customizer { it.requestMatchers("/observer/**").hasRole("ADMIN").anyRequest().permitAll() }
}
