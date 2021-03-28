package com.example.pokemon.home_activity.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokemon.R
import com.example.pokemon.commons.BaseActivity
import com.example.pokemon.databinding.ProfileFragmentBinding
import com.example.pokemon.home_activity.ui.home.vm.HomeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(){

    private val presenterHomeViewModel: HomeViewModel by viewModel()

    lateinit var fab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.background = null
        fab = findViewById(R.id.fab)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.x
        navView.setupWithNavController(navController)
    }

}

