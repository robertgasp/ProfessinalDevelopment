package com.example.professionaldevelopment.ui.main

import androidx.lifecycle.LiveData
import com.example.model.data.AppState
import com.example.professionaldevelopment.utils.parseSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) : com.example.core.BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLivaData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLivaData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, online: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLivaData.postValue(parseSearchResult(interactor.getData(word, online)))
        }


    override fun handleError(throwable: Throwable) {
        _mutableLivaData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        _mutableLivaData.value = AppState.Success(null)
        super.onCleared()
    }
}