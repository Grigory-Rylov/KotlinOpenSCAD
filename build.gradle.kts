plugins {
    kotlin("jvm") version "1.8.0"
    application
    `maven-publish`
}

group = "me.z"
version = "0.3.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "library"
            from(components["java"])
        }
    }
}
