plugins {
    id("dev.tavieto.android.feature")
    id("kotlin-parcelize")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.feature.main"

dependencies {
    implementation(project(":domain:auth"))
    implementation(project(":domain:movie"))
    implementation(libs.landscapist.glide)
}