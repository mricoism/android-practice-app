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
        maven(url = "https://storage.googleapis.com/download.flutter.io")
        maven(url = "/Users/code.id/Documents/learn/flutter/flutter_practice_app/inside_shell_app_android_native/flutter_module/build/host/outputs/repo")
    }
}

rootProject.name = "ShellAppExample"
include(":app")
