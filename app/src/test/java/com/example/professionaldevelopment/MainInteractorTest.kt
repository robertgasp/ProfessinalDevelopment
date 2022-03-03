package com.example.professionaldevelopment

import com.example.professionaldevelopment.model.dataSource.DataSourceLocal
import com.example.professionaldevelopment.model.dataSource.DataSourceRemote
import com.example.professionaldevelopment.model.repository.RepositoryImpl
import com.example.professionaldevelopment.ui.MainInteractor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainInteractorTest {

    private lateinit var mainInteractor: MainInteractor

    @Mock
    private lateinit var repositoryImpl: RepositoryImpl
    //private lateinit var dataSourceRemote: DataSourceRemote

    @Mock
    private lateinit var dataSourceLocal: DataSourceLocal

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)

//        val repositoryImplRemote = RepositoryImpl(dataSourceRemote)
//        val repositoryImplLocal = RepositoryImpl(dataSourceLocal)
        mainInteractor = MainInteractor(repositoryImpl,repositoryImpl)
    }

    @Test
    fun test1(){
        val word = "myWord"
        val isOnline = true
        verify(mainInteractor, times(1)).getData(word, isOnline)
    }
}