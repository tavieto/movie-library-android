plugins {
    id("dev.tavieto.android.library")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.data.server"

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":data:remote"))
    implementation(libs.koin.core)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.retrofit)
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interception)
    implementation(libs.kotlinx.coroutines.core)
}