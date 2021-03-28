package com.example.pokemon.home_activity.ui.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.models.PokemonDTO
import com.example.pokemon.R
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.commons.adapters.OnCLickListener
import com.example.pokemon.commons.adapters.PokemonAdapter
import com.example.pokemon.databinding.ProfileFragmentBinding
import com.example.pokemon.home_activity.ui.HomeActivity
import com.example.pokemon.home_activity.ui.profile.vm.ProfileViewModel
import com.example.pokemon.utils.SharedPokemonVM
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : BaseFragment(), OnCLickListener {

    private val presenter: ProfileViewModel by sharedViewModel()
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedPokemonVM: SharedPokemonVM by sharedViewModel()
    private lateinit var adapter: PokemonAdapter

    override fun loadObservers() {
        presenter.userName.observe(viewLifecycleOwner, {
            binding.tvUserName.setText(it)
        })

        presenter.userSurname.observe(viewLifecycleOwner, {
            binding.tvUserSurname.setText(it)
        })
        presenter.showMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
        presenter.showError.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })

        presenter.userAvatar.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                binding.ivProfileAvatarSprite.visibility = View.VISIBLE
                binding.ivProfileAvatarSprite.setImageResource(R.drawable.ic_user)

            } else {
                binding.ivProfileAvatarSprite.visibility = View.VISIBLE
                Picasso.get()
                        .load(it)
                        .into(binding.ivProfileAvatarSprite)
            }
        })
        presenter.transactionsList.observe(viewLifecycleOwner, {
            context?.let { context ->
                binding.recyclerViewProfile.layoutManager = LinearLayoutManager(activity)
                adapter = PokemonAdapter(it, this, context)
                binding.recyclerViewProfile.adapter = adapter
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        loadObservers()

        val mainActivity: HomeActivity? = activity as HomeActivity?
        mainActivity?.fab?.let { fab ->

            fab.setImageResource(android.R.drawable.ic_menu_save)
            fab.setOnClickListener {
                binding.tvUserName.text.toString().let {
                    presenter.saveUsername(it)
                }
                binding.tvUserSurname.text.toString().let {
                    presenter.saveUsersurname(it)
                }
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()


    }

    override fun toDetail(pokemon: PokemonDTO) {
        sharedPokemonVM.setTransaction(pokemon)
        findNavController().navigate(R.id.action_navigation_profile_to_navigation_detail)
    }

    override fun favoriteClick(pokemon: PokemonDTO) {
    }
}