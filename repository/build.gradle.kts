import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = Config.compile_sdk
}

dependencies {

    implementation(project(Modules.model))

    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation("com.google.android.material:material:1.5.0")
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)


    //Kotlin
    implementation(Kotlin.core)
    implementation(Kotlin.stdlib)

    //Retrofit 2
    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter_gson)
    implementation(Retrofit.adapter_coroutines)
    implementation(Retrofit.logging_interceptor)

    //Room
    implementation(Room.runtime)
    implementation(Room.compiler)
    implementation(Room.room_ktx)

    //Test
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)
}