plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val config by creating {
            if (System.getenv()["AE_DEPLOYMENT_ENV"] == "debug") {
                sourceSets["commonMain"].kotlin.srcDirs("src/debug/kotlin")
            } else {
                sourceSets["commonMain"].kotlin.srcDirs("src/prod/kotlin")
            }
        }

        val environmentMain by creating {
            sourceSets["commonMain"].kotlin.srcDirs("src/commonMain/kotlin")
            dependsOn(config)
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(environmentMain)

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "pro.aeternum.environment"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        jvmToolchain(11)
    }
}
