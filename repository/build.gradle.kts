plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":core:commons"))
    implementation(project(":domain:auth"))
    implementation(libs.koin.core)
    implementation(libs.kotlinx.coroutines.core)
}