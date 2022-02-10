package com.example.professionaldevelopment.di

import androidx.room.Room
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.RetrofitImpl
import com.example.professionaldevelopment.model.dataSource.RoomDataBaseImpl
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import com.example.professionaldevelopment.model.repository.RepositoryImplementationLocal
import com.example.professionaldevelopment.model.repository.RepositoryLocal
import com.example.professionaldevelopment.model.room.HistoryDataBase
import com.example.professionaldevelopment.ui.history.HistoryInteractor
import com.example.professionaldevelopment.ui.main.MainInteractor
import com.example.professionaldevelopment.ui.viewModel.MainViewModel
import com.example.professionaldevelopment.ui.viewModel.HistoryViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImpl(get()))}
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}

val historyScreen = module {
    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}