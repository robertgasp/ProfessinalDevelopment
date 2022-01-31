package com.example.professionaldevelopment.model.dataSource

import com.example.professionaldevelopment.model.data.DataModel
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import java.util.*

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}