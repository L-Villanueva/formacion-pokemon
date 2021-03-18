package com.example.pokemon.home_activity.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemon.R
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {

    private val presenter : HomeViewModel by sharedViewModel()
    private var _binding : HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun loadObservers() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        presenter.text.observe(viewLifecycleOwner, {
        })
        val view = binding.root
        return view
    }
}