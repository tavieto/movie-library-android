plugins {
    `kotlin-dsl`
}

group = "dev.tavieto.buildlogic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly("com.android.tools.build:gradle:8.0.1")
}

gradlePlugin {
    plugins {
        register("AndroidLibrary") {
            id = "dev.tavieto.android.library"
            implementationClass = "AndroidLibrary"
        }
        register("JetpackComposeConfig") {
            id = "dev.tavieto.android.compose"
            implementationClass = "AndroidJetpackCompose"
        }
    }
}