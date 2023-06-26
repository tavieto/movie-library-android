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
include(":data:firebase")
include(":data:local")
include(":data:remote")
include(":domain:auth")
include(":feature:auth")
include(":feature:main")
include(":repository")
