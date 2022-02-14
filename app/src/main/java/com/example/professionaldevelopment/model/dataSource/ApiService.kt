package com.example.professionaldevelopment.model.dataSource

import com.example.professionaldevelopment.model.data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import java.util.*

interface ApiService {
    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}