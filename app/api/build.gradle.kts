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
    implementation("io.github.oshai:kotlin-logging-jvm:${property("kotlinLoggingVersion")}")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation(project(":library:actuator"))
    implementation(project(":library:cloud-aws-s3"))
    implementation(project(":library:grpc"))
    implementation(project(":library:log"))
    implementation(project(":library:mongodb"))
    implementation(project(":library:mysql"))
    implementation(project(":library:redis"))
    implementation(project(":library:security"))
    implementation(project(":library:web"))

    implementation(project(":client:alarm"))
    implementation(project(":client:auth"))

    implementation(project(":core:board"))
    implementation(project(":core:comment"))
    implementation(project(":core:common"))
    implementation(project(":core:user"))

    implementation(project(":infrastructure:mongodb-adapter"))
    implementation(project(":infrastructure:mysql-adapter"))
    implementation(project(":infrastructure:redis-adapter"))

    implementation(project(":support"))
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
