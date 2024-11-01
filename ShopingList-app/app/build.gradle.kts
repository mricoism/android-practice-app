plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.shopinglist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.shopinglist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

//    implementation("com.google.android.gms:play-services-maps:19.0.0")
//        implementation("com.google.maps.android:maps:compose")
    implementation(libs.play.services.maps)
//    implementation(libs.maps)

        // Android Maps Compose composables for the Maps SDK for Android
        implementation(libs.maps.compose)

//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
//    implementation("com.squareup.retrofit2:retrofit")
//    implementation("com.squareup.retrofit2:converter-gson")
    // Solve problem https://stackoverflow.com/a/67319926 | Error: Failed to resolve: retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation(libs.androidx.lifecycle.viewmodel.compose)
//    implementation(libs.retrofit2.retrofit)
//    implementation(libs.retrofit2.converter.gson)
    implementation(libs.androidx.navigation.compose)
}