package com.example.data.remote

import com.example.data.models.ListPokemon
import com.example.data.models.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ITransactionAPI {
    @GET("{url}")
    suspend fun getPokemon(@Path("url") url : String): Response<PokemonDTO>
    @GET("api/v2/pokemon/")
    suspend fun getList(): Response<ListPokemon>
}