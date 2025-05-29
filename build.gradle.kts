// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application) apply false
    alias(libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(libs.plugins.com.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.spotless)
}
true // Needed to make the Suppress annotation work for the plugins block

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        android.set(true)
        ignoreFailures.set(false)
        reporters {
            org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
        }
        filter {
            exclude { element -> element.file.path.contains("generated/") }
        }
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("${project.rootDir}/build-logic/**/*.kt")
        }
        format("kts") {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
        }
    }
}