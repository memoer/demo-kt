import com.google.protobuf.gradle.id

dependencies {
    implementation(project(":demo-usecase"))

    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")

//    implementation("io.grpc:grpc-services")
//    implementation("org.springframework.grpc:spring-grpc-server-web-spring-boot-starter")
//    testImplementation("org.springframework.grpc:spring-grpc-test")

    //  implementation("org.springframework.kafka:spring-kafka")
    //  testImplementation("org.springframework.kafka:spring-kafka-test")
}

//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc"
//    }
//    plugins {
//        id("grpc") {
//            artifact = "io.grpc:protoc-gen-grpc-java"
//        }
//    }
//    generateProtoTasks {
//        all().forEach {
//            it.plugins {
//                id("grpc") {
//                    option("jakarta_omit")
//                    option("@generated=omit")
//                }
//            }
//        }
//    }
//}
//

