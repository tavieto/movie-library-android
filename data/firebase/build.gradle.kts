plugins {
    id("dev.tavieto.android.library")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.data.firebase"

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":data:remote"))
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.koin.core)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.firestore.ktx)
}