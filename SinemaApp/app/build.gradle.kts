plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // Make sure that you have the Google services Gradle plugin
    id("com.google.gms.google-services")
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.mricoism.sinemaapp"
    compileSdk = 35

    signingConfigs {
        create("release") {
            storeFile = file("/Users/code.id/Documents/learn/android/keystore/prod_keystore.jks")
            storePassword = "12345678"
            keyAlias = "key0"
            keyPassword = "12345678"
        }
    }

    defaultConfig {
        applicationId = "com.mricoism.sinemaapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
        release {
//            applicationIdSuffix = ".release" // Optional untuk menambahkan suffix pada prod
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release") // Link signingConfig
        }

        create("profile") {
            initWith(getByName("debug")) // Optional: Copy debug settings
            applicationIdSuffix = ".profile"
            isDebuggable = true // Enable debugging for profile build
        }
    }
    flavorDimensions += "app"

    productFlavors {
        create("development") {
            dimension = "app"
            applicationId = "com.mricoism.sinemaapp"
            versionName = "1.0"
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
        }
        create("production") {
            dimension = "app"
            applicationId = "com.mricoism.sinemaapp"
            versionName = "1.0-dev"
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
        }
        create("beta") {
            dimension = "app"
            applicationId = "com.mricoism.sinemaapp"
            versionName = "1.0-beta"
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.9.0"))

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-messaging")
}