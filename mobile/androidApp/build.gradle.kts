import pro.aeternum.AdAeternumVersions

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))

                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.ktx)
                implementation(libs.androidx.activity.compose)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "pro.aeternum"

    defaultConfig {
        applicationId = "pro.aeternum.AdAeternum"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionName = AdAeternumVersions.versionName
        versionCode = AdAeternumVersions.versionCode
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}
