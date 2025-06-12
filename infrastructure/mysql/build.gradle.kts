dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:user"))
    implementation(project(":support:util"))
    implementation(project(":library:mysql"))

    // QueryDSL dependencies
    implementation("com.querydsl:querydsl-jpa:${property("querydslVersion")}:jakarta")
    kapt("com.querydsl:querydsl-apt:${property("querydslVersion")}:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

// QueryDSL configuration
kapt {
    arguments {
        arg("querydsl.entityPathsForKotlin", "true")
    }
}
