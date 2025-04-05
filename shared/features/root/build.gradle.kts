plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "root"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared)
            implementation(projects.shared.entity)
            implementation(projects.shared.resources)
            implementation(projects.shared.features.base)
            implementation(projects.shared.features.splash)
            implementation(projects.shared.features.authorization)

            implementation(libs.koinCore)
            implementation(libs.bundles.moko.mvvm)
            implementation(libs.bundles.ktor)
            implementation(libs.multiplatformSettings)
        }

        androidMain.dependencies {
            implementation(libs.multiplatformSettings)
            implementation(libs.koinAndroid)
        }
    }
}

android {
    namespace = "com.studcenter.features.root"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
