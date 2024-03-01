plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

tasks.named("compileKotlin", org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask::class.java) {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}
