plugins {
    id("dev.tavieto.android.library")
    id("kotlin-kapt")
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
    implementation(libs.gson)
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
}