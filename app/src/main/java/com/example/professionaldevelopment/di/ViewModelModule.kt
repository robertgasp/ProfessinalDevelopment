package com.example.professionaldevelopment.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.professionaldevelopment.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel (mainModel:MainViewModel):ViewModel

}