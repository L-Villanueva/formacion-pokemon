package com.example.pokemon.home_activity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.databinding.ProfileFragmentBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : BaseFragment() {

    private val presenter: ProfileViewModel by sharedViewModel()
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun loadObservers() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        presenter.text.observe(viewLifecycleOwner, Observer {
            binding.textNotifications.text = it
        })

        return view
    }
}