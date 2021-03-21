package com.example.pokemon.home_activity.ui.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pokemon.commons.BaseFragment
import com.example.pokemon.databinding.ProfileFragmentBinding
import com.example.pokemon.home_activity.ui.profile.vm.ProfileViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : BaseFragment() {

    private val presenter: ProfileViewModel by sharedViewModel()
    private var _binding: ProfileFragmentBinding? = null
    private val binding get() = _binding!!

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
                binding.ivProfileAvatarSprite.visibility = View.GONE
            } else {
                binding.ivProfileAvatarSprite.visibility = View.VISIBLE
                Picasso.get()
                        .load(it)
                        .resize(400,400)
                        .into(binding.ivProfileAvatarSprite)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        loadObservers()
        binding.buttonSave.setOnClickListener {

            binding.tvUserName.text.toString().let {
                presenter.saveUsername(it)
            }
            binding.tvUserSurname.text.toString().let {
                presenter.saveUsersurname(it)
            }
        }

        return binding.root
    }
}