plugins {
    id("dev.tavieto.android.feature")
}

android.namespace = "dev.tavieto.movielibrary.feature.auth"

dependencies {
    implementation(project(":domain:auth"))
}