package com.example.professionaldevelopment.ui.main

import com.example.professionaldevelopment.di.NAME_LOCAL
import com.example.professionaldevelopment.di.NAME_REMOTE
import io.reactivex.Observable
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.ui.viewModel.Interactor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word)
        )
    }
}