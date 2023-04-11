import org.flywaydb.gradle.task.FlywayMigrateTask

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.10"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.flywaydb.flyway") version "9.8.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        runtimeOnly("org.postgresql:postgresql")
        // lombok dependencies
        implementation("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.projectlombok:lombok")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.postgresql:postgresql")
    compileOnly("org.flywaydb:flyway-core")
}

flyway {
    url = "jdbc:postgresql://localhost:5432/postgres"
    user = System.getenv("DB_USER") ?: ""
    password = System.getenv("DB_PASSWORD") ?: ""
    schemas = arrayOf("public")
    locations = arrayOf("filesystem:${project.rootDir}/db/migration")
    cleanDisabled = false
}

tasks.withType<FlywayMigrateTask> {
    dependsOn("classes")
}


