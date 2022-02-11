plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android{
    compileSdk = Config.compile_sdk

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(project(Modules.model))
    implementation(project(Modules.utils))
    implementation(project(Modules.repository))
    implementation(project(Modules.core))

    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    implementation(Kotlin.core)
    implementation(Design.appcompat)
    implementation("com.google.android.material:material:1.5.0")
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)


    //Koin for Android

    //Koin core features
    implementation ("io.insert-koin:koin-core:3.1.2")
    //Koin main features for Android (Scope,ViewModel ...)
    implementation ("io.insert-koin:koin-android:3.1.2")
    //Koin Java Compatibility
    implementation ("io.insert-koin:koin-android-compat:3.1.2")
}