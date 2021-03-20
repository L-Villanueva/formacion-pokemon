package com.example.pokemon.home_activity.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.commons.ErrorDialog
import com.example.pokemon.databinding.HomeFragmentBinding
import com.example.pokemon.home_activity.ui.home.vm.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {

    private val presenter : HomeViewModel by sharedViewModel()
    private var _binding : HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PokemonAdapter

    override fun loadObservers() {
        presenter.showError.observe(viewLifecycleOwner,{
            errorDialog = activity?.let { activity ->
                ErrorDialog(
                    activity,
                    "getString(R.string.alert)",
                    it,
                    "getString(R.string.close)"
                ) {
                    errorDialog?.dismiss()
                }
            }
            errorDialog!!.setCancelable(false)
            errorDialog!!.show()
        })
        presenter.text.observe(viewLifecycleOwner, {
        })
        presenter.transactionsList.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()){
                presenter.fetchTransactions()
            }
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = PokemonAdapter(it)
            binding.recyclerView.adapter = adapter
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        loadObservers()
        return binding.root
    }
}