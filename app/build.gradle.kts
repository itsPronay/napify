plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.compose.compiler)

    //hilt
//    id ("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.pronaycoding.blanket_mobile"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pronaycoding.blanket_mobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation("androidx.media:media:1.7.0")
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
//    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //navigation
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    //Exoplayer
    implementation("androidx.media3:media3-exoplayer:1.3.1")
    implementation("androidx.media3:media3-exoplayer-dash:1.3.1")
    implementation("androidx.media3:media3-ui:1.3.1")


    //await
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
//    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

//    //hilt
    implementation("com.google.dagger:hilt-android:2.54")
    kapt("com.google.dagger:hilt-android-compiler:2.54")
    implementation(libs.androidx.hilt.navigation.compose)

    // extra icons
    implementation("androidx.compose.material:material-icons-extended:1.5.1")

    // Room Database
    val room_version = "2.7.0-alpha08"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // Kotlinx Metadata JVM for Kotlin 2.1.0 compatibility
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.9.0")
//
//    implementation("com.google.dagger:hilt-android:2.44")
//    }
//}
//
//kapt {
//    useBuildCache = true
//    arguments {
//        arg("org.jetbrains.kotlin.kapt.use.light.analysis", true)
//    }
}

//kapt {
//    correctErrorTypes = true
//}