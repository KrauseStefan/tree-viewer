import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  kotlin("jvm") version "2.0.0"
  id("org.jetbrains.intellij") version "1.17.3"
}

group = "org.skk.tooling.idea"
version = "2.0.0"

repositories {
  mavenCentral()
}

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}

tasks {
  compileKotlin {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
  }

  compileTestKotlin {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
  }
}

dependencies {

}

// See https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2024.1.4")
}

tasks {
  buildSearchableOptions {
    enabled = false
  }

  patchPluginXml {
    version.set("${project.version}")
    sinceBuild.set("232")
    untilBuild.set("241.*")
  }
}
