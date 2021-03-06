package com.example.pokemon.home_activity.ui.home.vm

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
import kotlin.coroutines.coroutineContext

class HomeViewModel(private val repository: PokemonRepository, private val dataStoreRepository: DataStoreRepository) : BaseViewModel() {

    val transactionsList: LiveData<List<PokemonDTO>> = repository.mPokemon
    val nextUrl: LiveData<String> = dataStoreRepository.next.asLiveData()
    val previousUrl: LiveData<String> = dataStoreRepository.previous.asLiveData()

    fun fetchPokemons(){
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getPokemonList()){
                is ResultHandler.Success -> {
                    showMessage(result.data)
                }
                else -> {
                    setShowError(result)
                }
            }
            _isLoading.postValue(false)
        }
    }
    fun fetchPokemonsNext(url: String){
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getPokemonListNext(url)){
                is ResultHandler.Success -> {
                    showMessage(result.data)
                }
                else -> {
                    setShowError(result)
                }
            }
            _isLoading.postValue(false)
        }
    }
    fun editPokemon(pokemon: PokemonDTO) {

        repository.editPokemon(pokemon)
    }
}