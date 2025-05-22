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
    implementation(project(":demo-infra:actuator"))
    implementation(project(":demo-infra:grpc"))
    implementation(project(":demo-infra:logback"))
    implementation(project(":demo-infra:mongo"))
    implementation(project(":demo-infra:mysql"))
    implementation(project(":demo-infra:redis"))
    implementation(project(":demo-infra:security"))
    implementation(project(":demo-infra:web"))

    implementation(project(":demo-core:board"))
    implementation(project(":demo-core:comment"))
    implementation(project(":demo-core:common"))
    implementation(project(":demo-core:user"))

    implementation("io.github.oshai:kotlin-logging-jvm:${property("kotlin_logging_version")}")
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
