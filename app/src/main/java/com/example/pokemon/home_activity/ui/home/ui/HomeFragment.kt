package com.example.pokemon.home_activity.ui.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.models.PokemonDTO
import com.example.pokemon.R
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.commons.ErrorDialog
import com.example.pokemon.commons.adapters.OnCLickListener
import com.example.pokemon.commons.adapters.PokemonAdapter
import com.example.pokemon.databinding.HomeFragmentBinding
import com.example.pokemon.home_activity.ui.home.vm.HomeViewModel
import com.example.pokemon.utils.SharedPokemonVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() , OnCLickListener {

    private var _binding : HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private var first = true

    private val presenter : HomeViewModel by sharedViewModel()
    private val sharedPokemonVM: SharedPokemonVM by sharedViewModel()

    private lateinit var adapter: PokemonAdapter

    override fun loadObservers() {

        presenter.showError.observe(viewLifecycleOwner,{
            errorDialog = activity?.let { activity ->
                ErrorDialog(
                    activity,
                    getString(R.string.alert),
                    it,
                    getString(R.string.close)
                ) {
                    errorDialog?.dismiss()
                }
            }
            errorDialog!!.setCancelable(false)
            errorDialog!!.show()
        })

        presenter.transactionsList.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                if (first){
                    first = false
                    presenter.fetchPokemons()

                }
            }

            context?.let { context ->
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                adapter = PokemonAdapter(it, this, context)
                binding.recyclerView.adapter = adapter
            }

        })

        presenter.isLoading.observe(viewLifecycleOwner,{
            if (it) {
                binding.animationView.visibility = View.VISIBLE
                binding.buttonNext.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.animationView.visibility = View.GONE
                binding.buttonNext.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.VISIBLE
            }
        })

        presenter.nextUrl.observe(viewLifecycleOwner, {
           it.let { url ->
               binding.buttonNext.setOnClickListener {
                   presenter.fetchPokemonsNext(url)
               }
           }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        loadObservers()
        return binding.root
    }

    override fun click(pokemon: PokemonDTO) {
        sharedPokemonVM.setTransaction(pokemon)
        findNavController().navigate(R.id.action_navigation_home_to_navigation_detail)
    }
}