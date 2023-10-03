// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val agp_version by extra("8.1.1")
    dependencies {
        classpath("com.android.tools.build:gradle:$agp_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47")
    }
}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}