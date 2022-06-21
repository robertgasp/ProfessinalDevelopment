package com.example.repository.repository.dataSource



interface DataSource<T> {

    suspend fun getData(word: String): T

}