plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android{
    compileSdk = Config.compile_sdk
}

dependencies {

    implementation(project(Modules.model))
    implementation(project(Modules.utils))

    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation("com.google.android.material:material:1.5.0")
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
}


