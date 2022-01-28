package com.example.professionaldevelopment.model.repository

import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.dataSource.DataSource
import io.reactivex.Observable

class RepositoryImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}