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
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        val flutterStorageUrl = System.getenv("FLUTTER_STORAGE_BASE_URL") ?: "https://storage.googleapis.com"
        maven("$flutterStorageUrl/download.flutter.io")
    }
}

rootProject.name = "AppShellWithProjectSourcecode"
include(":app")

val filePath = settingsDir.parentFile.toString() + "/AppShellWithProjectSourcecode/flutter_sourcecode/.android/include_flutter.groovy"
//val filePath = "/Users/code.id/Documents/learn/android/practice-apps/AppShellWithProjectSourcecode/flutter_sourcecode/.android/include_flutter.groovy"
apply(from = File(filePath))

// Link issue related
// https://github.com/flutter/flutter/issues/158583
 