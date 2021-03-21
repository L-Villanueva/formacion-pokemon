package com.example.data.remote

import com.example.data.models.ListPokemon
import com.example.data.models.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {
    @GET("{url}")
    suspend fun getPokemon(@Path("url") url : String): Response<PokemonDTO>
    @GET("api/v2/pokemon/?limit=40")
    suspend fun getList(): Response<ListPokemon>
    @GET("api/v2/pokemon/?limit=40")
    suspend fun getListNext(@Query("offset") url : String): Response<ListPokemon>
}