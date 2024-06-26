import pro.aeternum.AdAeternumVersions

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":environment"))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.components.resources)

                implementation(libs.ktor.client)
                implementation(libs.ktor.client.negotiation)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.logging)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.ktx)
                implementation(libs.ktor.engine.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.ktor.engine.ios)
            }
        }

        sourceSets {
            all {
                languageSettings.optIn("kotlinx.serialization.ExperimentalSerializationApi")
                languageSettings.optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                languageSettings.optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "pro.aeternum.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res", "src/commonMain/resources")
    sourceSets["main"].resources.srcDirs("src/androidMain/res", "src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

val generateIOSVersion by tasks.register("generateIOSVersion") {
    val text = mapOf(
        "TEAM_ID" to "pro.aeternum",
        "BUNDLE_ID" to "AdAeternum",
        "APP_NAME" to "AD AETERNUM",
        "VERSION_NAME" to AdAeternumVersions.versionName,
        "VERSION_CODE" to AdAeternumVersions.versionCode,
    ).map { (key, value) -> "$key=$value" }.joinToString(separator = "\n")

    file(path = "../iosApp/Configuration/Config.xcconfig").writeText(text)
}

val embedAndSignAppleFrameworkForXcode: Task by tasks.getting {
    dependsOn(generateIOSVersion)
}
