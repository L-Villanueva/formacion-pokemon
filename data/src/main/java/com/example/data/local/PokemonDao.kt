package com.example.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.commons.Constants.TABLE_TRANSACTIONS
import com.example.data.models.PokemonDTO

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(pokemons: PokemonDTO)

    @Query("SELECT * FROM `$TABLE_TRANSACTIONS`")
    fun load(): LiveData<List<PokemonDTO>>

    @Query("DELETE FROM `$TABLE_TRANSACTIONS`")
    fun deleteAll()

    @Query("SELECT * FROM `$TABLE_TRANSACTIONS` WHERE favorite")
    fun loadFavorites(): LiveData<List<PokemonDTO>>

    @Update
    fun edit(pokemonDTO: PokemonDTO)
}