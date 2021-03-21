package com.example.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.R
import com.example.data.commons.BaseRepository
import com.example.data.remote.ResultHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class DataStoreRepository(private val context: Context):BaseRepository() {

    private val NEXT_URL = stringPreferencesKey("nextUrl")
    private val PREVIOUS_URL = stringPreferencesKey("previousUrl")
    private val USER_NAME = stringPreferencesKey("userName")
    private val USER_SURNAME = stringPreferencesKey("userSurame")
    private val USER_AVATAR = stringPreferencesKey("userAvatar")


    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val next: Flow<String> = context.dataStore.data.map {
        it[NEXT_URL] ?: ""
    }
    val previous: Flow<String> = context.dataStore.data.map {
        it[PREVIOUS_URL] ?: ""
    }
    val name: Flow<String> = context.dataStore.data.map {
        it[USER_NAME] ?: ""
    }
    val surname: Flow<String> = context.dataStore.data.map {
        it[USER_SURNAME] ?: ""
    }
    val avatar: Flow<String> = context.dataStore.data.map {
        it[USER_AVATAR] ?: ""
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
    suspend fun saveUserName( value: String ) : ResultHandler<String> {
        return try {
            context.dataStore.edit { settings ->
                settings[USER_NAME] = value
            }
            ResultHandler.Success(context.getString(R.string.success))
        } catch (throwable: Throwable){
            ResultHandler.GenericError(throwable.message)
        }

    }

    suspend fun saveUserSurname( value: String ) : ResultHandler<String>{
        return try {
            context.dataStore.edit { settings ->
                settings[USER_SURNAME] = value
            }
            ResultHandler.Success(context.getString(R.string.success))
        } catch (throwable: Throwable){
            ResultHandler.GenericError(throwable.message)
        }
    }

    suspend fun saveUserAvatar( value: String ) : ResultHandler<String>{
        return try {
            context.dataStore.edit { settings ->
                settings[USER_AVATAR] = value
            }
            ResultHandler.Success(context.getString(R.string.success))
        } catch (throwable: Throwable){
            ResultHandler.GenericError(throwable.message)
        }
    }
}