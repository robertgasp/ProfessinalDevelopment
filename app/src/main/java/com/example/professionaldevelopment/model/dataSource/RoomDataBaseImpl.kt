package com.example.professionaldevelopment.model.dataSource

import com.example.professionaldevelopment.di.historyScreen
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.room.HistoryDAO
import com.example.professionaldevelopment.utils.convertDataModelSuccessToEntity
import com.example.professionaldevelopment.utils.mapHistoryEntityToSearchResult
import io.reactivex.Observable

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