package com.example.professionaldevelopment.application

import android.app.Application
import com.example.professionaldevelopment.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TranslatorApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchAndroidInjector
    }

    //    override fun onCreate() {
//        super.onCreate()
//        DaggerAppComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)


    companion object {
        val componemt: AppComponent by lazy {
            DaggerAppCompobent.builder().build()
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}