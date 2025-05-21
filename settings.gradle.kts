rootProject.name = "demo"

include (
    "demo-app:api",
    "demo-app:admin",

    "demo-interface:api",
    "demo-interface:admin",

    "demo-infra:grpc",
    "demo-infra:kafka",
    "demo-infra:logback",
    "demo-infra:mongo",
    "demo-infra:mysql",
    "demo-infra:redis",
    "demo-infra:spring-actuator",
    "demo-infra:spring-security",
    "demo-infra:spring-web",

    "demo-usecase",

    "demo-domain",

    "demo-lib"
)

pluginManagement {
    val kotlin_version: String by settings
    val spring_boot_version: String by settings
    val spring_dependency_management_version: String by settings
    val protobuf_version: String by settings
    val kt_lint_version: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlin_version)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlin_version)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlin_version)

                "org.springframework.boot" -> useVersion(spring_boot_version)
                "io.spring.dependency-management" -> useVersion(spring_dependency_management_version)
                "com.google.protobuf" -> useVersion(protobuf_version)
                "org.jlleitschuh.gradle.ktlint" -> useVersion(kt_lint_version)
            }
        }
    }
}