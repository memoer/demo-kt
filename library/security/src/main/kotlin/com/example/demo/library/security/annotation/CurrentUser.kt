package com.example.demo.library.security.annotation

import org.springframework.security.core.annotation.AuthenticationPrincipal
import kotlin.annotation.AnnotationRetention
import kotlin.annotation.AnnotationTarget

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : user")
annotation class CurrentUser
