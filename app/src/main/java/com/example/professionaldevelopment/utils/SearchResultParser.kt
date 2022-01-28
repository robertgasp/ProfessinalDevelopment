package com.example.professionaldevelopment.utils

import com.example.professionaldevelopment.model.data.AppState
import com.example.professionaldevelopment.model.data.DataModel
import com.example.professionaldevelopment.model.data.Meanings

fun parseSearchResult(data: AppState): AppState {
    val newSearchResult = arrayListOf<DataModel>()
    when (data) {
        is AppState.Success -> {
            val searchResults = data.data
            if (!searchResults.isNullOrEmpty()) {
                for (searchResult in searchResults) {
                    parseResult(searchResult, newSearchResult)
                }
            }
        }
    }
    return AppState.Success(newSearchResult)
}

private fun parseResult(dataModel: DataModel, newDataModels: ArrayList<DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in dataModel.meanings) {
            if (meaning.translation != null && meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(DataModel(dataModel.text, newMeanings))
        }
    }

    fun convertingMeaningsToString(meanings: List<Meanings>):String{
        var meaningsSeparatedByComma = String()
        for((index,meaning) in meanings.withIndex()){
            meaningsSeparatedByComma +=  if(index+1!=meanings.size){
                String.format("%s%s", meaning.translation.translation,", ")
            }else
                meaning.translation?.translation
        }
        return meaningsSeparatedByComma
    }
}
