package com.example.professionaldevelopment.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.rx.SchedulerProvider
import com.example.professionaldevelopment.rx.SchedulerProviderImpl
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : AppState>(
    protected val liveDataToObserveForView: MutableLiveData<T> = MutableLiveData(),
    protected  val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected  val schedulerProvider: SchedulerProvider = SchedulerProviderImpl()
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataToObserveForView

    override fun onCleared() {
        compositeDisposable.clear()
    }
}