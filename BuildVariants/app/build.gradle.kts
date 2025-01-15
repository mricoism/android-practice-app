plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mricoism.buildvariants"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mricoism.buildvariants"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
            // buildConfigField("String", "BASE_URL", "\"https://www.google.com\"") // buildTypes also can define buildConfigField
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
        release {
            applicationIdSuffix = ".prod"
            // buildConfigField("String", "BASE_URL", "\"https://www.apple.com\"") // buildTypes also can define buildConfigField
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        create("profile") {
            initWith(getByName("debug")) // Optional: Copy debug settings
            isDebuggable = true         // Enable debugging for profile build
        }
    }

    flavorDimensions += "app"
    productFlavors {
        create("production") {
            dimension = "app"
            applicationId = "com.example.prod"
            versionName = "1.0"
            buildConfigField("String", "BASE_URL", "\"https://www.apple.com\"")
        }

        create("beta") {
            dimension = "app"
            applicationId = "com.example.beta"
            versionName = "1.0-beta"
            buildConfigField("String", "BASE_URL", "\"https://www.google.com\"")
        }

        create("dev") {
            dimension = "app"
            applicationId = "com.example.dev"
            versionName = "1.0-dev"
            buildConfigField("String", "BASE_URL", "\"https://www.microsoft.com\"")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}