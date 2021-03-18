package com.example.pokemon.di

import com.example.pokemon.home_activity.ui.home.vm.HomeViewModel
import com.example.pokemon.home_activity.ui.profile.vm.ProfileViewModel
import com.example.pokemon.splash_activity.ui.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
        viewModel { HomeViewModel (get()) }
        viewModel { ProfileViewModel() }
        viewModel { SplashViewModel(get()) }
}