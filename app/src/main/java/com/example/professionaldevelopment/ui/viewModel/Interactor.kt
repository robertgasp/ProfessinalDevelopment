package com.example.professionaldevelopment.ui.viewModel

import com.example.professionaldevelopment.model.data.AppState
import io.reactivex.Observable

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}