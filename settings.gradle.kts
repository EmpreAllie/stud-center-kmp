enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StudCenterQueue"
include(":androidApp")
include(":androidApp:features")
include(":androidApp:ui")
include(":androidApp:features:root")
include(":androidApp:features:screen:splash")
include(":androidApp:features:screen:authorization")
include(":androidApp:features:screen:display")
include(":androidApp:features:screen:menu")
include(":androidApp:features:screen:record")
include(":androidApp:features:screen:role")
include(":shared")
include(":shared:entity")
include(":shared:resources")
include(":shared:features:root")
include(":shared:features:base")
include(":shared:features:splash")
include(":shared:features:authorization")
include(":shared:features:display")
include(":shared:features:menu")
include(":shared:features:record")
include(":shared:features:role")
include(":iosExport")
