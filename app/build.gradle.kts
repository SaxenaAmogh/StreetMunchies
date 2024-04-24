plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.reviewapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.reviewapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {    
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.okhttp)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.androidx.camera.camera2.v110alpha02)
    implementation (libs.androidx.camera.lifecycle.v110alpha02)
    implementation (libs.androidx.camera.view)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle.v110)
    implementation (libs.camera.core)
    implementation (libs.androidx.camera.camera2.v110)
    implementation (libs.androidx.camera.core.v110)
    implementation (libs.androidx.camera.camera2)
    implementation (libs.androidx.camera.lifecycle)
    implementation (libs.androidx.lifecycle.runtime)
    implementation (libs.androidx.lifecycle.viewmodel)
    implementation (libs.androidx.lifecycle.livedata)
    implementation (libs.androidx.lifecycle.common.java8)
    implementation (libs.androidx.lifecycle.reactivestreams)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.camera.core)
    implementation("androidx.exifinterface:exifinterface:1.3.6")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}