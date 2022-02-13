package com.example.repository.repository

import com.example.model.data.DataModel
import com.example.repository.repository.dataSource.DataSource


class RepositoryImpl(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}