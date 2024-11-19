import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  	id("org.springframework.boot") version "3.1.0"
  	id("io.spring.dependency-management") version "1.1.0"
  	kotlin("jvm") version "1.8.21"
  	kotlin("plugin.spring") version "1.8.21"
	kotlin("plugin.serialization") version "1.8.21"
}

group = "academy.softserve"
version = "0.0.1-SNAPSHOT"
java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}



tasks.register<Exec>("npmInstall") {
    workingDir = file("ui") // The working directory is the 'ui' folder
    commandLine("C:\\Program Files\\nodejs\\npm.cmd", "install")
}

tasks.register<Exec>("compileUi") {
    workingDir = file("ui") // The working directory is the 'ui' folder
    commandLine("C:\\Program Files\\nodejs\\npm.cmd", "run", "build")
    dependsOn("npmInstall")
}

tasks.register<Copy>("copyUi") {
    doFirst {
        // Create necessary folders if they don't exist
        file("src/main/resources/static").mkdirs()
        file("src/main/resources/static/css").mkdirs()
    }

    // Copy built files
    from("ui/dist") {
        include("index.html") // Copy index.html
        include("app.js") // Copy app.js
    }
    from("ui/dist/css") {
        include("style.css") // Copy style.css
        into("src/main/resources/static/css") // Place in the correct folder
    }
    into("src/main/resources/static")
    dependsOn("compileUi")
}

tasks.named("processResources") {
    dependsOn("copyUi")
}

tasks.withType<org.springframework.boot.gradle.tasks.run.BootRun> {
    dependsOn("copyUi") // Ensure 'copyUi' runs before BootRun
}
