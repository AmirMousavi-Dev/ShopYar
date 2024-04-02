plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "ir.codroid.database"

}
dependencies {
    "implementation"(libs.androidx.room.runtime)
    "kapt"(libs.androidx.room.compiler)
    "implementation"(libs.androidx.room.ktx)
}
