dependencies {
    implementation(project(":support:error"))

    api("org.springframework.boot:spring-boot-starter-web")
    implementation("io.github.oshai:kotlin-logging-jvm:${property("kotlinLoggingVersion")}")
}
