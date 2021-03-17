package com.example.pokemon.commons

import android.app.Application
import com.example.data.di.dataModule
import com.example.pokemon.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    //Empezamos la app configurando koin para la inyeccion de dependencias
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(uiModule, dataModule))
        }
    }
}