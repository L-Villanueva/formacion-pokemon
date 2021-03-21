package com.example.pokemon.home_activity.ui.detail.vm

import androidx.lifecycle.viewModelScope
import com.example.data.remote.ResultHandler
import com.example.data.repositories.DataStoreRepository
import com.example.pokemon.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(private val dataStoreRepository: DataStoreRepository): BaseViewModel() {

    fun saveUserAvatar(avatar: String)  {
        viewModelScope.launch (Dispatchers.IO){
            when (val result = dataStoreRepository.saveUserAvatar(avatar)) {
                is ResultHandler.Success -> showMessage(result.data)
                else -> setShowError(result)
            }
        }
    }
}