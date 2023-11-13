import org.gradle.kotlin.dsl.support.uppercaseFirstChar

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
        val environmentMain by creating {
            val environment: Map<String, String> = if (file("local.env").exists()) {
                mapEnvironment(fileName = "local.env")
            } else {
                mapOf("AA_DEPLOYMENT_ENV" to "prod")
            }

            if (environment["AA_DEPLOYMENT_ENV"] == null) {
                throw IllegalStateException("AA_DEPLOYMENT_ENV environment needs to be set. Values: prod | debug.")
            }

            val environmentPath = "environment${environment["AA_DEPLOYMENT_ENV"]!!.uppercaseFirstChar()}"

            sourceSets["commonMain"].kotlin.srcDirs(
                "src/commonMain/kotlin",
                "src/$environmentPath/kotlin",
            )
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

fun mapEnvironment(fileName: String): Map<String, String> = file(path = fileName)
    .readLines()
    .associate { line ->
        val pos = line.indexOf("=")
        val key = line.substring(0, pos)
        val value = line.substring(pos + 1)

        key to value
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
