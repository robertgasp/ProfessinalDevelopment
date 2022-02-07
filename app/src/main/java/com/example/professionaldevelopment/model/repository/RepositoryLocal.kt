package com.example.professionaldevelopment.model.repository

import com.example.professionaldevelopment.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveDB(appState: AppState)
}