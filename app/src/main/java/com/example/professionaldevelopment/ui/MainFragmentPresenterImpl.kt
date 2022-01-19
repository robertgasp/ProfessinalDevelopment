package com.example.professionaldevelopment.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.dataSource.DataSourceLocal
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import com.example.professionaldevelopment.presenter.MainFragmentPresenter
import com.example.professionaldevelopment.rx.SchedulerProvider
import com.example.professionaldevelopment.rx.SchedulerProviderImpl
import com.example.professionaldevelopment.ui.base.RenderView
import com.example.professionaldevelopment.ui.fragments.MainScreenFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.observers.DisposableObserver
import javax.sql.DataSource

class MainFragmentPresenterImpl<T : AppState, V : RenderView>(
    private var interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemote()),
        RepositoryImpl(DataSourceLocal())
    ),
    protected val schedulerProvider: SchedulerProviderImpl = SchedulerProviderImpl(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

) : MainFragmentPresenter<T> {

    private val renderView: V? = null


    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(word, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { renderView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver()))
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                renderView?.renderData(appState)
            }

            override fun onError(error: Throwable) {
                renderView?.renderData(AppState.Error(error))
            }

            override fun onComplete() {
            }

        }
    }
}