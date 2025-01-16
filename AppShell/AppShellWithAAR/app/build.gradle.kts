plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mricoism.appshellwithaar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mricoism.appshellwithaar"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {

        }
        //AppShellConfig
        create("profile") {
            initWith(getByName("debug"))
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
    }
}

//AppShellConfig
configurations {
    getByName("profileImplementation") {
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

    //AppShellConfig
//    debugImplementation("com.example.flutter_module:flutter_debug:1.0")
//    releaseImplementation("com.example.flutter_module:flutter_release:1.0")
//    add("profileImplementation", "com.example.flutter_module:flutter_profile:1.0")

    debugImplementation("com.baf.codeid.flutter_module:flutter_debug:1.0")
    releaseImplementation("com.baf.codeid.flutter_module:flutter_release:1.0")
    add("profileImplementation", "com.baf.codeid.flutter_module:flutter_profile:1.0")
}