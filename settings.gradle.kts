plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "advent-of-code"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
include("2024")
include("2023")
include("2022")
include("2021")
include("common")
