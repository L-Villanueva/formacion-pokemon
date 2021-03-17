package com.example.pokemon.commons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    //Siempre tendremos observadores en los fragment asi que los configuramos en el base y luego heredamos de el

    abstract fun loadObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadObservers()
    }

}