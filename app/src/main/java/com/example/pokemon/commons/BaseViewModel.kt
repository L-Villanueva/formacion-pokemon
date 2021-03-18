package com.example.pokemon.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.remote.ResultHandler
import com.example.pokemon.utils.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {

    protected var _showMessage = SingleLiveEvent<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    protected var _showError = SingleLiveEvent<String>()
    val showError: LiveData<String>
        get() = _showError


    protected var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun showMessage(text: String){
        _showMessage.postValue(text)
    }

    fun setShowError(resultHandler: ResultHandler<Any>){
        when (resultHandler){
            is ResultHandler.NetworkError -> {
                _showError.postValue("")
            }
            is ResultHandler.HttpError -> {
                _showError.postValue("${resultHandler.code!!}")
            }
            is ResultHandler.GenericError -> {
                _showError.postValue(resultHandler.message!!)
            }
            else -> {
                _showError.postValue("")
            }
        }
    }
}