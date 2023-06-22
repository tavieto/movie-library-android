package dev.tavieto.movielibrary.buildlogic

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureAndroidDefaultSettings(
    commonExtension: LibraryExtension
) {
    commonExtension.apply {
        compileSdk = 33
        defaultConfig {
            minSdk = 24

            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        sourceSets {
            this.getByName("main").java.srcDir("src/main/kotlin")
        }
    }
}

