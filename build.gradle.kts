plugins {
    id("com.android.application") version ("7.4.2") apply false
    id("com.android.library") version ("7.4.2") apply false
    id("org.jetbrains.kotlin.android") version ("1.7.10") apply false
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.6")
    }
}