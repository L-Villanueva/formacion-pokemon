package com.example.pokemon.home_activity.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pokemon.commons.BaseViewModel

class ProfileViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}