package com.example.professionaldevelopment.model.repository

import androidx.lifecycle.ViewModel
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.DataSourceLocal

class RepositoryImplementationLocal(
    private val dataSource: DataSourceLocal<List<DataModel>>
):RepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }
}