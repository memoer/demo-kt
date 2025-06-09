package com.example.demo.library.security.annotation

import org.springframework.security.access.prepost.PreAuthorize
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.AnnotationTarget

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
annotation class AuthAdmin
