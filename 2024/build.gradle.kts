plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.benchmark") version "0.4.13"
    kotlin("plugin.allopen") version "2.0.20"
}
allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":common"))
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.13")
}

benchmark {
    targets {
        register("main")
    }
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}