import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "2.0.21"
}

group = "dev.kolibrium.demo.dsl"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("dev.kolibrium:kolibrium-dsl:0.5.0-SNAPSHOT")
    implementation("dev.kolibrium:kolibrium-selenium:0.5.0-SNAPSHOT")
    implementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("org.assertj:assertj-core:3.26.3")
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