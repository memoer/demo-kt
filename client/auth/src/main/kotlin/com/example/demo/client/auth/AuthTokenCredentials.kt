package com.example.demo.client.auth

import io.grpc.CallCredentials
import io.grpc.Metadata
import java.util.concurrent.Executor

class AuthTokenCredentials(private val token: String) : CallCredentials() {
    override fun applyRequestMetadata(
        requestInfo: RequestInfo?,
        appExecutor: Executor?,
        applier: MetadataApplier?,
    ) {
        val headerName = "x-custom-auth"
        Runnable {
            val metadata = Metadata()
            val key: Metadata.Key<String?>? = Metadata.Key.of(headerName, Metadata.ASCII_STRING_MARSHALLER)
            metadata.put(key, token)
            applier!!.apply(metadata)
        }.run {
            appExecutor!!.execute(this)
        }
    }
}
