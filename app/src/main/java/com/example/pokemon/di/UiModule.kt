package com.example.pokemon.di

import com.example.pokemon.home_activity.ui.home.HomeViewModel
import com.example.pokemon.home_activity.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
        viewModel { HomeViewModel ()}
        viewModel { ProfileViewModel() }
}