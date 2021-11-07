import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    kotlin("plugin.jpa") version "1.5.21"
}

group = "ua.tarch64"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
}

object Versions {
    const val SPRING = "2.5.6"
    const val JSON_WEB_TOKEN = "0.11.2"
    const val POSTGRES = "42.3.1"
    const val JACKSON = "2.13.0"
    const val FREEMARKER = "2.3.14"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.SPRING}")
    implementation("org.springframework.boot:spring-boot-starter-validation:${Versions.SPRING}")

    implementation("org.springframework.boot:spring-boot-starter-mail:${Versions.SPRING}")
    implementation("org.freemarker:freemarker:${Versions.FREEMARKER}")

    implementation("org.springframework.boot:spring-boot-starter-security:${Versions.SPRING}")
    implementation("io.jsonwebtoken:jjwt-api:${Versions.JSON_WEB_TOKEN}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${Versions.JSON_WEB_TOKEN}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${Versions.JSON_WEB_TOKEN}")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Versions.SPRING}")
    runtimeOnly("org.postgresql:postgresql:${Versions.POSTGRES}")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "16"
    }
}

