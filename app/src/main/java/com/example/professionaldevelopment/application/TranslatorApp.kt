package com.example.professionaldevelopment.application

import android.app.Application
import com.example.professionaldevelopment.di.application
import com.example.professionaldevelopment.di.historyScreen
import com.example.professionaldevelopment.di.mainScreen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import javax.inject.Inject

class TranslatorApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}