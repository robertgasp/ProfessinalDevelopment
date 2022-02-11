package com.example.repository.repository

import com.example.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveDB(appState: AppState)
}