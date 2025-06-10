package com.example.demo.cloudawss3

import io.awspring.cloud.s3.ObjectMetadata
import io.awspring.cloud.s3.S3Resource
import io.awspring.cloud.s3.S3Template
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.services.s3.model.ObjectCannedACL
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.time.Duration

@Service
class AwsS3Service(private val template: S3Template, private val props: AwsS3Props) {

    fun save(key: String, inputStream: InputStream, metadata: ObjectMetadata?): S3Resource = template.upload(props.bucketName, key, inputStream, metadata)

    @Throws(IOException::class)
    fun save(key: String, file: MultipartFile, acl: ACL) {
        val metadata = ObjectMetadata.builder()
            .acl(map(acl))
            .contentLength(file.size)
            .contentType(file.contentType)
            .build()
        file.inputStream.use { save(key, it, metadata) }
    }

    fun find(key: String): S3Resource = template.download(props.bucketName, key)

    fun delete(key: String) {
        template.deleteObject(props.bucketName, key)
    }

    fun signedPutUrl(key: String, acl: ACL, contentType: String?): URL {
        val duration = Duration.ofMinutes(30)
        val metadata = ObjectMetadata.builder().acl(map(acl)).contentType(contentType).build()
        return template.createSignedPutURL(props.bucketName, key, duration, metadata, contentType)
    }

    fun singedGetUrl(key: String): URL {
        val duration = Duration.ofMinutes(60)
        return template.createSignedGetURL(props.bucketName, key, duration)
    }

    private fun map(acl: ACL) = ObjectCannedACL.valueOf(acl.name)

    enum class ACL(val value: String?) {
        PRIVATE("private"),
        PUBLIC_READ("public-read"),
        PUBLIC_READ_WRITE("public-read-write"),
        AUTHENTICATED_READ("authenticated-read"),
        AWS_EXEC_READ("aws-exec-read"),
        BUCKET_OWNER_READ("bucket-owner-read"),
        BUCKET_OWNER_FULL_CONTROL("bucket-owner-full-control"),
        UNKNOWN_TO_SDK_VERSION(null),
    }
}
