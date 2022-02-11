package com.example.professionaldevelopment.di

import androidx.room.Room
import com.example.model.data.DataModel
import com.example.repository.repository.dataSource.RetrofitImpl
import com.example.repository.repository.dataSource.RoomDataBaseImpl
import com.example.repository.repository.Repository
import com.example.repository.repository.RepositoryImpl
import com.example.repository.repository.RepositoryImplementationLocal
import com.example.repository.repository.RepositoryLocal
import com.example.repository.repository.room.HistoryDataBase
import com.example.historyscreen.HistoryInteractor
import com.example.professionaldevelopment.ui.main.MainInteractor
import com.example.professionaldevelopment.ui.viewModel.MainViewModel
import com.example.historyscreen.HistoryViewModel
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