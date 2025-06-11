import com.google.protobuf.gradle.id

plugins {
    id("com.google.protobuf")
}

dependencies {
    implementation(project(":support:util"))

    implementation("org.springframework.grpc:spring-grpc-spring-boot-starter")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
            }
        }
    }
}
