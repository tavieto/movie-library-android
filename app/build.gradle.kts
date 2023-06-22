plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.tavieto.android.compose")
}

apply {
    from("$rootDir/android-common.gradle")
}

android {
    namespace = "dev.tavieto.movielibrary.app"
    defaultConfig.applicationId = "dev.tavieto.movielibrary.app"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit)
//    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
//    androidTestImplementation platform('androidx.compose:compose-bom:2022.10.00')
//    androidTestImplementation 'androidx.compose.ui:ui-test-junit4'
//    debugImplementation 'androidx.compose.ui:ui-tooling'
//    debugImplementation 'androidx.compose.ui:ui-test-manifest'
}