plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "ir.codroid.merchandise_data"
}



dependencies {
    implementation(project(":core"))
    implementation(project(":database"))
    implementation(project(":merchandise:merchandise_domain"))

}