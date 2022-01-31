package com.example.professionaldevelopment.ui

import com.example.professionaldevelopment.di.NAME_LOCAL
import com.example.professionaldevelopment.di.NAME_REMOTE
import io.reactivex.Observable
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.ui.viewModel.Interactor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE)private val remoteRepository: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {

        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}