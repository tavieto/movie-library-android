plugins {
    id("dev.tavieto.android.library")
    id("dev.tavieto.android.compose")
}

apply {
    from("$rootDir/android-common.gradle")
}

android.namespace = "dev.tavieto.movielibrary.core.uikit"

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}