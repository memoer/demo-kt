dependencies {
    implementation(project(":demo-domain"))
    implementation(project(":demo-usecase"))
    implementation(project(":demo-lib"))

    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}