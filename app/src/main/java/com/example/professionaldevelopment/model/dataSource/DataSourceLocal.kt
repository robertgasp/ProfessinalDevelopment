package com.example.professionaldevelopment.model.dataSource

import com.example.professionaldevelopment.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal(private val remoteProvider: RoomDataBaseImpl = RoomDataBaseImpl()):DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}