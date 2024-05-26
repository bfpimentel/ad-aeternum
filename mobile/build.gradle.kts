plugins {
    `version-catalog`
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
}

subprojects {
    apply {
        plugin("org.gradle.version-catalog")
    }

    catalog {
        versionCatalog {
            from(files("libs.versions.toml"))
        }
    }
}
