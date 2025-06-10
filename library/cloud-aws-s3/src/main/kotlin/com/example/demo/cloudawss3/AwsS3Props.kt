package com.example.demo.cloudawss3

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.cloud.aws.s3")
data class AwsS3Props(val bucketName: String)
