package com.example.professionaldevelopment.di

import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.ui.MainInteractor
import dagger.Module
import dagger.Provides
import java.util.jar.Attributes
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}