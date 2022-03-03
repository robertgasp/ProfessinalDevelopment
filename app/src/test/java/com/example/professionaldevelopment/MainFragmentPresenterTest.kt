package com.example.professionaldevelopment

import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.dataSource.DataSourceLocal
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import com.example.professionaldevelopment.presenter.MainFragmentPresenter
import com.example.professionaldevelopment.rx.SchedulerProvider
import com.example.professionaldevelopment.rx.SchedulerProviderImpl
import com.example.professionaldevelopment.ui.MainFragmentPresenterImpl
import com.example.professionaldevelopment.ui.MainInteractor
import com.example.professionaldevelopment.ui.base.RenderView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MainFragmentPresenterTest {

    private lateinit var mainFragmentPresenter: MainFragmentPresenterImpl<AppState, RenderView>

    @Mock
    private lateinit var interactor: MainInteractor

//    @Mock
//    private lateinit var dataSourceRemote: DataSourceRemote
//
//    @Mock
//    private lateinit var dataSourceLocal:DataSourceLocal
//
    @Mock
    private lateinit var schedulerProvider: SchedulerProviderImpl

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable
//
//    @Mock
//    private lateinit var renderView: RenderView

    class MainTestInteractor(
        val dataSourceRemote: DataSourceRemote,
        val dataSourceLocal: DataSourceLocal
    ): MainInteractor {
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
//        val repositoryImplLocal = RepositoryImpl(dataSourceLocal)
//        val repositoryImplRemote = RepositoryImpl(dataSourceRemote)
//        val interactor2 = MainTestInteractor(dataSourceRemote,dataSourceLocal)


        mainFragmentPresenter =
            MainFragmentPresenterImpl(interactor, schedulerProvider, compositeDisposable)
    }

    @Test
    fun getDataTesting() {
        val word = "my word"
        val isOnline = true
        verify(mainFragmentPresenter, times(1)).getData(word, isOnline)
    }

}