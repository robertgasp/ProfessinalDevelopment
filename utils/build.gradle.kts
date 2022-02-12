plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    compileSdk = Config.compile_sdk
}

dependencies {

    implementation(Design.appcompat)

    //Kotlin
    implementation(Kotlin.core)
    implementation(Kotlin.stdlib)

    implementation(Design.material)

    implementation(Kotlin.coroutines_core)
    implementation(Kotlin.coroutines_android)

    //Test
    testImplementation(TestImpl.junit)
    androidTestImplementation(TestImpl.runner)
    androidTestImplementation(TestImpl.espresso)

    //Koin for Android
    //Koin core features
    implementation("io.insert-koin:koin-core:3.1.2")
    //Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:3.1.2")
    //Koin Java Compatibility
    implementation("io.insert-koin:koin-android-compat:3.1.2")

}
