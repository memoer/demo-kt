dependencies {
    implementation(project(":library"))

    implementation("org.springframework.boot:spring-boot-starter-security")

    testImplementation("org.springframework.security:spring-security-test")

    compileOnly("jakarta.servlet:jakarta.servlet-api")
}
