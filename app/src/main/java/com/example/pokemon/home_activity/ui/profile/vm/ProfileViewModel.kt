package com.example.pokemon.home_activity.ui.profile.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.models.PokemonDTO
import com.example.data.remote.ResultHandler
import com.example.data.repositories.DataStoreRepository
import com.example.data.repositories.PokemonRepository
import com.example.pokemon.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: PokemonRepository,  private val dataStoreRepository: DataStoreRepository) : BaseViewModel() {

    val transactionsList: LiveData<List<PokemonDTO>> = repository.mPokemonFavorite
    val userName: LiveData<String> = dataStoreRepository.name.asLiveData()
    val userSurname: LiveData<String> = dataStoreRepository.surname.asLiveData()
    val userAvatar: LiveData<String> = dataStoreRepository.avatar.asLiveData()

    fun saveUsername(name: String)  {
        viewModelScope.launch (Dispatchers.IO){
            when (val result = dataStoreRepository.saveUserName(name)) {
                is ResultHandler.Success -> showMessage(result.data)
                else -> setShowError(result)
            }
        }
    }
    fun saveUsersurname(name: String)  {
        viewModelScope.launch (Dispatchers.IO){
            when (val result = dataStoreRepository.saveUserSurname(name)) {
                is ResultHandler.Success -> showMessage(result.data)
                else -> setShowError(result)
            }
        }
    }
}