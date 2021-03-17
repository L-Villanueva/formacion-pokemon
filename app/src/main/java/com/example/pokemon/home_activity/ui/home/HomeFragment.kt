package com.example.pokemon.home_activity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pokemon.R
import com.example.pokemon.commons.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {

    private val presenter : HomeViewModel by sharedViewModel()

    override fun loadObservers() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        presenter.text.observe(viewLifecycleOwner, {
        })

        return root
    }
}