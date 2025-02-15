import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "2.1.10"
    id("com.google.devtools.ksp") version "2.1.10-1.0.29"
}

group = "dev.kolibrium.demo.ksp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("dev.kolibrium:kolibrium-annotations:0.6.0")
    implementation("dev.kolibrium:kolibrium-dsl:0.6.0")
    implementation("dev.kolibrium:kolibrium-selenium:0.6.0")
    ksp("dev.kolibrium:kolibrium-processors:0.6.0")
    ksp("dev.zacsweers.autoservice:auto-service-ksp:1.2.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.4")
}

ksp {
    arg("kolibriumKsp.useDsl", "true")
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