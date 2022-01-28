package com.example.professionaldevelopment.model.dataSource

import io.reactivex.Observable

interface DataSource<T> {

    suspend fun getData(word: String): T

}