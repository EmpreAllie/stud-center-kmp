plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.studcenter.ui"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(projects.shared)
    implementation(projects.shared.resources)
    implementation(projects.shared.entity)
    implementation(projects.shared.features.base)

    implementation(libs.picasso)

    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.moko.network)
    debugImplementation(libs.compose.ui.tooling.preview)
}