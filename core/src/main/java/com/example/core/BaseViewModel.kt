package com.example.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.data.AppState
import kotlinx.coroutines.*


abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLivaData: MutableLiveData<T> = MutableLiveData(),

    ) : ViewModel() {


    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) }
    )

    abstract fun handleError(throwable: Throwable)

    abstract fun getData(word: String, isOnline: Boolean)

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}