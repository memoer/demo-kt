plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false

    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    id("com.google.protobuf") apply false

    id("org.jlleitschuh.gradle.ktlint") apply false
    id("org.sonarqube")
    id("jacoco")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of("${property("javaVersion")}")
    }
}

allprojects {
    group = "${property("projectGroup")}"
    version = "${property("appVersion")}"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.sonarqube")
    apply(plugin = "jacoco")

    dependencyManagement {
        imports {
            mavenBom("org.springframework.grpc:spring-grpc-dependencies:${property("springGrpcVersion")}")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("io.kotest:kotest-runner-junit5:${property("kotestVersion")}")
        testImplementation("io.kotest:kotest-assertions-core:${property("kotestVersion")}")
        testImplementation("io.mockk:mockk:${property("mockkVersion")}")
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of("${property("javaVersion")}")
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    jacoco {
        toolVersion = "0.8.13"
        reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }

    tasks.test {
        useJUnitPlatform()
        reports {
            html.required.set(false)
            junitXml.required.set(false)
        }
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test) // tests are required to run before generating the report
    }

    tasks.jacocoTestReport {
        reports {
            xml.required = false
            csv.required = false
            html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
        }
    }
}

sonar {
    properties {
        property("sonar.projectName", "demo")
        property("sonar.projectVersion", "${property("appVersion")}")
        property("sonar.projectKey", "demo")
        property("sonar.language", "kotlin")

        property("sonar.sourceEncoding", "UTF-8")

        property("sonar.host.url", "${property("sonarqubeHost")}")
        property("sonar.token", "${property("sonarqubeToken")}")

        property(
            "sonar.modules",
            listOf(
                "demo-app",
                "demo-client",
                "demo-core",
                "demo-infra",
                "demo-lib",
            ),
        )
    }
}
