import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "2.0.21"
    id("com.google.devtools.ksp") version "2.0.21-1.0.26"
}

group = "dev.kolibrium.demo.junit.with.project.config"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kolibrium:kolibrium-annotations:0.4.0")
    implementation("dev.kolibrium:kolibrium-dsl:0.4.0")
    implementation("dev.kolibrium:kolibrium-junit:0.4.0")
    implementation("dev.kolibrium:kolibrium-selenium:0.4.0")
    ksp("dev.kolibrium:kolibrium-ksp:0.4.0")
    ksp("dev.zacsweers.autoservice:auto-service-ksp:1.2.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    compilerOptions.freeCompilerArgs = listOf(
        "-Xcontext-receivers",
    )
}

kotlin {
    jvmToolchain(21)
}