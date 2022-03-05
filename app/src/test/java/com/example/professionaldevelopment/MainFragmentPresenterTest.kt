package com.example.professionaldevelopment


import android.annotation.SuppressLint
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.data.Meanings
import com.example.professionaldevelopment.model.data.Translation
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
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Response


class MainFragmentPresenterTest {

    private lateinit var mainFragmentPresenter: MainFragmentPresenterImpl<AppState, RenderView>

    class Producer() {
        fun just(): Observable<List<DataModel>> {
            return Observable.just(
                listOf(
                    DataModel(
                        "word",
                        listOf(Meanings(Translation("слово")), Meanings(Translation("Слово")))
                    ),
                )
            )
        }
    }

    @Mock
    private lateinit var interactor: MainInteractor

    @Mock
    private lateinit var schedulerProvider: SchedulerProviderImpl

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mainFragmentPresenter =
            MainFragmentPresenterImpl(interactor, schedulerProvider, compositeDisposable)
    }

    @SuppressLint("CheckResult")
    @Test
    fun getDataTesting() {
        val word = "word"
        val isOnline = true

        val producer = Producer()
        fun returnObservableAppState(): Observable<AppState> {
            return producer.just().map { AppState.Success(it) }
        }

        val data = returnObservableAppState()

        `when`(interactor.getData(word, isOnline)).thenReturn(data)

        val expectedData = listOf(
            DataModel(
                "word",
                listOf(Meanings(Translation("слово")), Meanings(Translation("Слово")))
            ),
        )
        Assert.assertEquals(data, interactor.getData(word, isOnline))
    }

    @Test
    fun verifyGetData() {
        val word = "word"
        val isOnline = true
        verify(interactor, times(1)).getData(word, isOnline)
    }

}