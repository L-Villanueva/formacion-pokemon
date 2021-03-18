package com.example.pokemon.splash_activity.ui

import android.os.Bundle
import com.example.pokemon.commons.BaseActivity
import com.example.pokemon.databinding.ActivitySplashBinding
import com.example.pokemon.home_activity.ui.HomeActivity
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity: BaseActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val presenter : SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        presenter.fetchTransactions()
        object: Thread(){
            override fun run(){
                try{
                    sleep(2000)
                }catch (ie: InterruptedException){
                    ie.printStackTrace()
                }finally {
                    startActivity<HomeActivity>()
                    finish()
                }
            }
        }.start()
    }
}