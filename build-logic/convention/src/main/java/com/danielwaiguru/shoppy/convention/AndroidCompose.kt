package com.danielwaiguru.shoppy.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findBundle("compose").get())

            add("androidTestImplementation", platform(bom))
            add("androidTestImplementation", libs.findLibrary("ui-test-junit4").get())

            add("debugImplementation", libs.findBundle("compose-testing-manifest").get())
        }
    }
}
