plugins {
    id("dev.tavieto.android.feature")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.feature.auth"

dependencies {
    implementation(project(":domain:auth"))
}