dependencies {
    implementation(project(":core:user"))
    implementation(project(":support"))
    implementation(project(":library:mysql"))
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}
