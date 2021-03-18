package com.example.pokemon.splash_activity.ui

import androidx.lifecycle.viewModelScope
import com.example.data.remote.ResultHandler
import com.example.data.repositories.TransactionRepository
import com.example.pokemon.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: TransactionRepository): BaseViewModel() {

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