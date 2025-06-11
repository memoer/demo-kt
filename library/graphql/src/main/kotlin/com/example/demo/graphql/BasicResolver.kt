package com.example.demo.graphql

import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BasicResolver {
    @QueryMapping("hello")
    fun query(): Boolean = true

    @MutationMapping("hello")
    fun mutation(): Boolean = true
}
