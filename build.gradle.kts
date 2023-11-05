plugins {
    `version-catalog`
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.compose).apply(false)
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
