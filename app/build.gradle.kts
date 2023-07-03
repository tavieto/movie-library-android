plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.tavieto.android.compose")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

apply {
    from("$rootDir/android-common.gradle")
}

android {
    namespace = "dev.tavieto.movielibrary.app"
    defaultConfig {
        applicationId = "dev.tavieto.movielibrary.app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:uikit"))
    implementation(project(":core:commons"))
    implementation(project(":core:core"))
    implementation(project(":data:firebase"))
    implementation(project(":data:local"))
    implementation(project(":data:remote"))
    implementation(project(":data:server"))
    implementation(project(":domain:auth"))
    implementation(project(":domain:movie"))
    implementation(project(":repository"))

    implementation(libs.androidx.core.ktx)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    testImplementation(libs.junit)
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
//    androidTestImplementation platform('androidx.compose:compose-bom:2022.10.00')
//    androidTestImplementation 'androidx.compose.ui:ui-test-junit4'
//    debugImplementation 'androidx.compose.ui:ui-tooling'
//    debugImplementation 'androidx.compose.ui:ui-test-manifest'
}