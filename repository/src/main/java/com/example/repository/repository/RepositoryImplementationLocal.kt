package com.example.repository.repository

import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.repository.repository.dataSource.DataSourceLocal

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