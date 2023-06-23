plugins {
    id("kotlin")
}

dependencies {
    implementation(project(":core:commons"))
    implementation(libs.koin.core)
    implementation(libs.kotlinx.coroutines.core)
}