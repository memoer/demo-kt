dependencies {
    implementation(project(":support:util"))
    implementation(project(":client:auth"))

    implementation("org.springframework.boot:spring-boot-starter-security")

    testImplementation("org.springframework.security:spring-security-test")

    compileOnly("jakarta.servlet:jakarta.servlet-api")
}
