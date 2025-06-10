dependencies {
    implementation(platform("io.awspring.cloud:spring-cloud-aws-dependencies:${property("awspringCloudVersion")}"))
    implementation("io.awspring.cloud:spring-cloud-aws-starter-s3")
    implementation("org.springframework:spring-web")
}
