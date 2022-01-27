package com.example.professionaldevelopment.model.repository

import io.reactivex.Observable

interface Repository<T> {

    suspend fun getData(word: String): T
}