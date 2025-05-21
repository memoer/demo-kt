tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":demo-interface:api"))

    implementation(project(":demo-infra:grpc"))
    implementation(project(":demo-infra:kafka"))
    implementation(project(":demo-infra:logback"))
    implementation(project(":demo-infra:mongo"))
    implementation(project(":demo-infra:mysql"))
    implementation(project(":demo-infra:redis"))
    implementation(project(":demo-infra:spring-actuator"))
    implementation(project(":demo-infra:spring-security"))
    implementation(project(":demo-infra:spring-web"))

    implementation("org.springframework.boot:spring-boot-starter")
}