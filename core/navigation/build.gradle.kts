plugins {
    id("dev.tavieto.android.library")
}

android.namespace = "dev.tavieto.movielibrary.core.navigation"

dependencies {
    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation)

    // navigation
    implementation(libs.accompanist.navigation)
    implementation(libs.androidx.navigation.common.ktx)

    // dependency injection
    implementation(libs.koin.androidx.compose)
}