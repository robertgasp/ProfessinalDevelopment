package com.example.professionaldevelopment.ui

import androidx.lifecycle.LiveData
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.dataSource.DataSourceLocal
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import com.example.professionaldevelopment.ui.viewModel.BaseViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun subscribe(): LiveData<AppState> {
        return liveDataToObserveForView
    }

    private fun doOnSubscribe(): (Disposable) -> Unit =
        { liveDataToObserveForView.value = AppState.Loading(null) }

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { doOnSubscribe()}
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                liveDataToObserveForView.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataToObserveForView.value = AppState.Error(e)
            }

            override fun onComplete() {

            }

        }
    }
}