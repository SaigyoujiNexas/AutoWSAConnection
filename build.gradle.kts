plugins {
  id("java")
  id("org.jetbrains.intellij") version "1.16.0"
  kotlin("jvm") version "1.9.21"
}

group = "com.saigyoujinexas"
version = "1.7"

repositories {
  mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2022.2.5")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf("android"))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("222")
    untilBuild.set("")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
kotlin {
  sourceSets.all {
    languageSettings{
      languageVersion = "2.0"
    }
  }
}
dependencies {
}