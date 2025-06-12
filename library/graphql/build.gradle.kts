dependencies {
    implementation(project(":support:error"))

    api("org.springframework.boot:spring-boot-starter-graphql")
    implementation("com.graphql-java:graphql-java-extended-scalars:${property("graphqlScalarVersion")}")

    testImplementation("org.springframework.graphql:spring-graphql-test")
}
