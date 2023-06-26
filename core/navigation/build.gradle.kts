plugins {
    id("dev.tavieto.android.library")
    id("dev.tavieto.android.compose")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.core.navigation"

dependencies {
    implementation(project(":core:commons"))

    // project features
    implementation(project(":feature:main"))
    implementation(project(":feature:auth"))

    // navigation
    api(libs.androidx.navigation)
    api(libs.androidx.navigation.common.ktx)

    // dependency injection
    implementation(libs.koin.androidx.compose)

    // gson
    implementation(libs.gson)
}