plugins {
    id("dev.tavieto.android.library")
}

apply {
    from("$rootDir/android-common.gradle")
}

android {
    namespace = "dev.tavieto.movielibrary.data.remote"
}

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":repository"))
    implementation(libs.koin.core)
    implementation(libs.kotlinx.coroutines.core)
}