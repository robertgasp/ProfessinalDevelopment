package com.example.professionaldevelopment.presenter

import android.database.Observable
import android.view.View
import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.ui.base.RenderView

interface MainFragmentPresenter<T : AppState> {

    fun getData(word: String, isOnline: Boolean)
}