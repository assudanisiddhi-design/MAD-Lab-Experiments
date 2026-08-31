pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MAD Lab Experiments"
include(":Exp-1:app")
include(":Exp-2:app")
include(":Exp-3:app")
include(":Exp-4:app")
include(":Exp-5:app")
include(":Exp-6:app")
