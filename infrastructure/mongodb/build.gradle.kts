dependencies {
    implementation(project(":support:util"))
    implementation(project(":core:comment"))
    implementation(project(":library:mongodb"))

    // QueryDSL dependencies
    implementation("com.querydsl:querydsl-mongodb:${property("querydslVersion")}")
    kapt("com.querydsl:querydsl-apt:${property("querydslVersion")}:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

kapt {
    annotationProcessor("org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor")
}
