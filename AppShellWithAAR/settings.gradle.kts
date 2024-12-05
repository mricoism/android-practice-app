pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        //AppShellConfig
        maven(url = "https://storage.googleapis.com/download.flutter.io")
//        maven(url = "../libs")
        maven(url = "/Users/code.id/Documents/learn/android/practice-apps/AppShellWithAAR/app/libs")
    }
}

rootProject.name = "AppShellWithAAR"
include(":app")
