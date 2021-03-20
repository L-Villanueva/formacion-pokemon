package com.example.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.commons.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepository(private val context: Context):BaseRepository() {

    private val NEXT_URL = stringPreferencesKey("nextUrl")
    private val PREVIOUS_URL = stringPreferencesKey("previousUrl")

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val next: Flow<String> = context.dataStore.data.map {
        it[NEXT_URL] ?: ""
    }
    val previous: Flow<String> = context.dataStore.data.map {
        it[PREVIOUS_URL] ?: ""
    }
    suspend fun saveNextDataStore(url: String) {
        context.dataStore.edit { settings ->
            settings[NEXT_URL] = url
        }
    }
    suspend fun savePreviousDataStore(url: String) {
        context.dataStore.edit { settings ->
            settings[PREVIOUS_URL] = url
        }
    }
}