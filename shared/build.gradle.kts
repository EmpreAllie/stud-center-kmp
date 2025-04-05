plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    id("dev.icerock.mobile.multiplatform-network-generator")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.entity)

            implementation(libs.koinCore)
            implementation(libs.bundles.moko.mvvm)
            implementation(libs.bundles.ktor)
            implementation(libs.bundles.moko.network)
            implementation(libs.kotlinxDateTime)
            implementation(libs.kotlinSerialization)
            implementation(libs.multiplatformSettings)
        }
    }
}

android {
    namespace = "com.studcenter"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
        buildConfigField("int", "VERSION_CODE", "1")
        buildConfigField("String", "VERSION_NAME", "\"1.0\"")
        externalNativeBuild {
            ndkBuild {
                cppFlags += ""
            }
        }
    }

    externalNativeBuild {
        ndkBuild {
            path = file("src/androidMain/kotlin/com/studcenter/jni/Android.mk")
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

mokoNetwork {
    spec("serverApi") {
        inputSpec = file("src/api/openapi.yaml")
        isInternal = false
    }
}