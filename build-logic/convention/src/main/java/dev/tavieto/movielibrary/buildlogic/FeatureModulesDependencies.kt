package dev.tavieto.movielibrary.buildlogic

import com.android.build.api.dsl.CommonExtension
import dev.tavieto.movielibrary.buildlogic.extension.getLibrary
import dev.tavieto.movielibrary.buildlogic.extension.implementation
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureFeatureDependencies(
    commonExtension: CommonExtension<*, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        dependencies {
            implementation(project(":core:commons"))
            implementation(project(":core:core"))
            implementation(project(":core:design"))

            implementation(libs.getLibrary("koin-core"))
        }
    }
}
