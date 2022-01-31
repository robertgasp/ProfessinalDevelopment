package com.example.professionaldevelopment.di

import android.app.Application
import com.example.professionaldevelopment.ui.fragments.MainScreenFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        FragmentModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)


@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: MainScreenFragment)
}