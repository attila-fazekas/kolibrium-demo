rootProject.name = "kolibrium-demo"

include("dsl")
include("junit")
include("junit:with-project-config")
findProject(":junit:with-project-config")?.name = "with-project-config"
include("junit:without-project-config")
findProject(":junit:without-project-config")?.name = "without-project-config"
include("ksp")
include("selenium")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
