import com.google.protobuf.gradle.id

plugins {
    id("com.google.protobuf")
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":infrastructure:actuator"))
    implementation(project(":infrastructure:logback"))
    implementation(project(":infrastructure:web"))

    implementation(project(":core:board"))
    implementation(project(":core:comment"))
    implementation(project(":core:common"))
    implementation(project(":core:user"))

    implementation("io.github.oshai:kotlin-logging-jvm:${property("kotlinLoggingVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation")
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
