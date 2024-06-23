import org.gradle.model.internal.typeregistration.InstanceFactory.ImplementationInfo

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.apep_2024_group_4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.apep_2024_group_4"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures{
        viewBinding = true
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

    dependencies {
        val room_version = "2.6.1"
        implementation ("androidx.room:room-runtime:$room_version")
        annotationProcessor ("androidx.room:room-compiler:$room_version")
    }

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}