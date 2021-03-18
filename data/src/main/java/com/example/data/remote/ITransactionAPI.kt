package com.example.data.remote

import com.example.data.models.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET

interface ITransactionAPI {
    @GET("/transactions.json")
    suspend fun getTransactions(): Response<PokemonDTO>
}