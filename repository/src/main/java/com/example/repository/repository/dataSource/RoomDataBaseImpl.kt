package com.example.repository.repository.dataSource

import com.example.model.data.AppState
import com.example.model.data.DataModel
import com.example.professionaldevelopment.utils.convertDataModelSuccessToEntity
import com.example.professionaldevelopment.utils.mapHistoryEntityToSearchResult
import com.example.repository.repository.room.HistoryDAO


class RoomDataBaseImpl(
    private var historyDAO: HistoryDAO
) : DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDAO.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let{
            historyDAO.insert(it)
        }
    }
}