package com.example.demo.client.auth

import com.example.demo.support.LoggedInUser
import org.springframework.stereotype.Service

@Service
class AuthClient(private val stub: AuthServiceGrpc.AuthServiceBlockingStub) {
    fun verify(token: String): Response {
        val request = Empty.newBuilder().build()
        val credentials = AuthTokenCredentials(token)
        val response: ValidateResponse = stub.withCallCredentials(credentials).verify(request)
        return Response(response.id, LoggedInUser.Type.valueOf(response.type), response.name)
    }

    data class Response(val id: Int, val type: LoggedInUser.Type, val name: String)
}
