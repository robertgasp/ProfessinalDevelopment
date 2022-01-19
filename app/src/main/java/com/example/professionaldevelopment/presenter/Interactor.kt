package com.example.professionaldevelopment.presenter

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}