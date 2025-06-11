rootProject.name = "demo"

include(
    "app:api",
    "app:admin",

    "client:alarm",
    "client:auth",
    "client:user",

    "core:user",
    "core:board",
    "core:comment",
    "core:common",

    "infrastructure:mongodb",
    "infrastructure:mysql",
    "infrastructure:redis",

    "library:cloud-aws-s3",
    "library:graphql",
    "library:grpc",
    "library:log",
    "library:mongodb",
    "library:mysql",
    "library:redis",
    "library:actuator",
    "library:security",
    "library:web",

    "support:error",
    "support:util",
)

project(":infrastructure:mongodb").name = "mongodb-adapter"
project(":infrastructure:mysql").name = "mysql-adapter"
project(":infrastructure:redis").name = "redis-adapter"

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val protobufVersion: String by settings
    val graphqlVersion: String by settings
    val ktLintVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)

                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "com.google.protobuf" -> useVersion(protobufVersion)
                "com.netflix.dgs.codegen" -> useVersion(graphqlVersion)

                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktLintVersion)
            }
        }
    }
}
