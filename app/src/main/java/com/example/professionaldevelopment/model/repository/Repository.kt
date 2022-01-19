package com.example.professionaldevelopment.model.repository

import io.reactivex.Observable

interface Repository<T> {

    fun getData(word: String): io.reactivex.Observable<T>
}