package com.example.pokemon.home_activity.ui.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.models.PokemonDTO
import com.example.data.remote.ResultHandler
import com.example.data.repositories.TransactionRepository
import com.example.pokemon.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TransactionRepository) : BaseViewModel() {

    val transactionsList: LiveData<List<PokemonDTO>> = repository.mTransactions

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchTransactions(){
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getTransactionsAndSave()){
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
}