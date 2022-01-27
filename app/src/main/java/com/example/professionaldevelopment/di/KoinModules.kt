package com.example.professionaldevelopment.di

import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.dataSource.RetrofitImpl
import com.example.professionaldevelopment.model.dataSource.RoomDataBaseImpl
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import com.example.professionaldevelopment.ui.MainInteractor
import com.example.professionaldevelopment.ui.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import javax.sql.DataSource

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitImpl()) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImpl(RoomDataBaseImpl()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)),get(named(NAME_LOCAL)))  }
    factory { MainViewModel (get()) }
}