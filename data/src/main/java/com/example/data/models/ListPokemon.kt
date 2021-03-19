package com.example.data.models

data class ListPokemon (
        val count: Long,
        val next: String?,
        val previous: String?,
        val results: List<Result>
)

data class Result (
        val name: String,
        val url: String
)
