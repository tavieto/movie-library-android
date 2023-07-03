plugins {
    id("dev.tavieto.android.library")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.repository"

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":domain:auth"))
    implementation(project(":domain:movie"))
    implementation(libs.koin.core)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.paging.runtime.ktx)
}