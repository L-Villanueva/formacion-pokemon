package com.example.data.di

import com.example.data.di.providers.*
import org.koin.dsl.module

val dataModule = module {

    single { provideOkHttpClient(get()) }
    single { provideMockInterceptor(get()) }
    single { provideGson()}
    single { provideRetrofit( get(), get())}
    single { providePokemonApi( get() )}
    single { provideBankDatabase( get() ) }
    single { providePokemonRepository( get(), get() , get())}
    single { provideDataStoreRepository( get() ) }

}