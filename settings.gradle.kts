rootProject.name = "demo"

include(
    "demo-app:api",
    "demo-app:admin",

    "demo-client:alarm",
    "demo-client:auth",

    "demo-core:user",
    "demo-core:board",
    "demo-core:comment",
    "demo-core:common",

    "demo-infra:grpc",
    "demo-infra:logback",
    "demo-infra:mongo",
    "demo-infra:mysql",
    "demo-infra:redis",
    "demo-infra:actuator",
    "demo-infra:security",
    "demo-infra:web",

    "demo-lib",
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val protobufVersion: String by settings
    val ktLintVersion: String by settings
    val sonarqubeVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)

                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "com.google.protobuf" -> useVersion(protobufVersion)
                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktLintVersion)
                "org.sonarqube" -> useVersion(sonarqubeVersion)
            }
        }
    }
}
