package com.example.professionaldevelopment.model.repository

import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.DataSource
import io.reactivex.Observable

class RepositoryImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}