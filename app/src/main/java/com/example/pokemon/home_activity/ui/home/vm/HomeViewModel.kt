package com.example.pokemon.home_activity.ui.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.models.TransactionDTO
import com.example.data.repositories.TransactionRepository
import com.example.pokemon.commons.BaseViewModel

class HomeViewModel(private val repository: TransactionRepository) : BaseViewModel() {

    val transactionsList: LiveData<List<TransactionDTO>> = repository.mTransactions

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}