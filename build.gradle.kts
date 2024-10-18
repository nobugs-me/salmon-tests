plugins {
    kotlin("jvm") version "2.0.10"
    id("io.qameta.allure") version "2.12.0"
}

group = "ph.salmon.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.qameta.allure:allure-rest-assured:2.29.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    implementation("io.rest-assured:rest-assured:5.5.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.11.0")
    testImplementation(kotlin("test"))
    testImplementation(platform("io.qameta.allure:allure-bom:2.29.0"))
    testImplementation("io.qameta.allure:allure-junit5:2.29.0")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
kotlin {
    jvmToolchain(21)
}
