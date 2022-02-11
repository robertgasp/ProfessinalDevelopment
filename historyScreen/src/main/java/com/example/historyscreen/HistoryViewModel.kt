package com.example.historyscreen

import androidx.lifecycle.LiveData

import com.example.model.data.AppState
import com.example.professionaldevelopment.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) : com.example.core.BaseViewModel<AppState>() {

    private val liveDataToObserve: LiveData<AppState> = _mutableLivaData

    fun subscribe(): LiveData<AppState> {
        return liveDataToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLivaData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLivaData.postValue(parseLocalSearchResults(interactor.getData(word, isOnline)))
    }

    override fun handleError(throwable: Throwable) {
        _mutableLivaData.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        _mutableLivaData.postValue(AppState.Success(null))
        super.onCleared()
    }
}