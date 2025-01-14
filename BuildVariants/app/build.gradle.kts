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
        release {
            applicationIdSuffix = ".prod"
            buildConfigField("String", "BASE_URL", "\"https://www.apple.com\"")
            isMinifyEnabled = true
//            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            applicationIdSuffix = ".dev"
            buildConfigField("String", "BASE_URL", "\"https://www.google.com\"")
            isMinifyEnabled = false
//            isShrinkResources = false
            isDebuggable = true


        }
    }
    productFlavors {
//        create("free") {
//            dimension = "version"
//            applicationId = "com.example.free"
//            versionName = "1.0-free"
//        }
//
//        create("paid") {
//            dimension = "version"
//            applicationId = "com.example.paid"
//            versionName = "1.0-paid"
//        }
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