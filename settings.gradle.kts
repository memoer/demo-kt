rootProject.name = "demo"

include(
    "app:api",
    "app:admin",

    "client:alarm",
    "client:auth",

    "core:user",
    "core:board",
    "core:comment",
    "core:common",

    "infrastructure:mysql",
    "infrastructure:mongodb",
    "infrastructure:redis",

    "library:grpc",
    "library:logback",
    "library:mongodb",
    "library:mysql",
    "library:redis",
    "library:actuator",
    "library:security",
    "library:web",

    "support",
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val protobufVersion: String by settings
    val ktLintVersion: String by settings

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
            }
        }
    }
}
