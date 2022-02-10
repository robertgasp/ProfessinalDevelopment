package com.example.professionaldevelopment.ui.history

import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.repository.Repository
import com.example.professionaldevelopment.model.repository.RepositoryLocal
import com.example.professionaldevelopment.ui.viewModel.Interactor

class HistoryInteractor(
    private val repositoryRemote: Repository<List<DataModel>>,
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
):Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if(fromRemoteSource){
                repositoryRemote
            }else{
                repositoryLocal
            }.getData(word)
        )
    }
}