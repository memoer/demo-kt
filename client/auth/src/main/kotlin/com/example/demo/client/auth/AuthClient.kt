package com.example.demo.client.auth

import com.example.demo.support.CustomUser
import org.springframework.stereotype.Service

@Service
class AuthClient(private val stub: AuthServiceGrpc.AuthServiceBlockingStub) {
    fun verify(token: String): Response {
        val request = Empty.newBuilder().build()
        val credentials = AuthTokenCredentials(token)
        val response: ValidateResponse = stub.withCallCredentials(credentials).verify(request)
        return Response(response.id, CustomUser.Type.valueOf(response.type), response.name)
    }

    data class Response(val id: Int, val type: CustomUser.Type, val name: String)
}
