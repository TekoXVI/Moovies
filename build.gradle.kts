// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    alias(libs.plugins.safe.args.kotlin) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.dagger.hilt.android) apply false
}

buildscript {
    dependencies {
        classpath(libs.androidx.navigation.safe.args)
    }
}