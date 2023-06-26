plugins {
    id("dev.tavieto.android.library")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.data.local"

dependencies {
    implementation(project(":repository"))
    implementation(libs.koin.android)
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.coroutines.core)
}