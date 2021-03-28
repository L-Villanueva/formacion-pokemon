package com.example.pokemon.home_activity.ui.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.commons.adapters.DetailAdapter
import com.example.pokemon.databinding.DetailFragmentBinding
import com.example.pokemon.home_activity.ui.detail.vm.DetailViewModel
import com.example.pokemon.utils.SharedPokemonVM
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment: BaseFragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedPokemonVM: SharedPokemonVM by sharedViewModel()
    private val presenter: DetailViewModel by sharedViewModel()

    private lateinit var typeAdapter: DetailAdapter
    private lateinit var statsAdapter: StatsAdapter

    override fun loadObservers() {

        presenter.showMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        presenter.showError.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        sharedPokemonVM.transaction.observe(viewLifecycleOwner, { pokemonValue ->
            binding.tvDetailPokemonName.text = pokemonValue.name.capitalize()
            pokemonValue.sprites?.front_default?.let {
                Picasso.get()
                        .load(it)
                        .resize(400,400)
                        .into(binding.ivDetailPokemonSprite)
                binding.ivDetailPokemonSprite.setOnClickListener { _ ->
                    presenter.saveUserAvatar(it)
                }
            }
            val layout = LinearLayoutManager(context)
            layout.orientation = RecyclerView.HORIZONTAL
            binding.typeRecyclerView.layoutManager = layout
            typeAdapter = DetailAdapter(pokemonValue.types)
            binding.typeRecyclerView.adapter = typeAdapter

            binding.statsRecyclerView.layoutManager = LinearLayoutManager(context)
            statsAdapter = StatsAdapter(pokemonValue.stats)
            binding.statsRecyclerView.adapter = statsAdapter

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        loadObservers()


        return binding.root
    }
}