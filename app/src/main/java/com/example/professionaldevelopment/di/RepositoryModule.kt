package com.example.professionaldevelopment.di

import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.DataSource
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.dataSource.RetrofitImpl
import com.example.professionaldevelopment.model.dataSource.RoomDataBaseImpl
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImpl()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun providesRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun providesDataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImpl()
}