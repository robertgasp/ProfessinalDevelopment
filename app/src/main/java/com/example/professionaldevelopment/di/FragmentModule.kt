package com.example.professionaldevelopment.di

import com.example.professionaldevelopment.ui.fragments.MainScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeMainScreenFragment(): MainScreenFragment
}