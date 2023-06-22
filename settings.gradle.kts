pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Movie Library"
include(":app")
include(":core:core")
include(":core:commons")
include(":core:navigation")
include(":core:uikit")
include(":data:remote")
include(":data:local")
include(":domain:auth")
include(":feature:auth")
include(":repository")
