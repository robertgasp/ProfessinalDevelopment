package com.example.professionaldevelopment.utils

import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel

fun parseSearchResult(data: AppState): AppState {
    val newSearchResult = arrayListOf<DataModel>()
    when (data){
        is AppState.Success ->{
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()){
                for (searchResult in searchResults){
                    parseResult(searchResult,newSearchResult)
                }
            }
        }
    }
    return AppState.Success(newSearchResult)
}

fun parseResult(searchResult: DataModel, newSearchResult: ArrayList<DataModel>) {

}
