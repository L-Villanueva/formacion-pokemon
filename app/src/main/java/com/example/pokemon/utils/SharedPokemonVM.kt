package com.example.pokemon.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.models.PokemonDTO
import com.example.pokemon.commons.BaseViewModel

class SharedPokemonVM : BaseViewModel(){

    private var _transaction = MutableLiveData<PokemonDTO>()

    val transaction: LiveData<PokemonDTO>
    get() = _transaction

    fun setTransaction(transaction: PokemonDTO){
        _transaction.value = transaction
    }

}