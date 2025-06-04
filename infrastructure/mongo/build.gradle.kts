dependencies {
    implementation(project(":library"))
    implementation(project(":core:comment"))

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}
