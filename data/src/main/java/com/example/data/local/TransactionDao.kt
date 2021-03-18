package com.example.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.commons.Constants.TABLE_TRANSACTIONS
import com.example.data.models.PokemonDTO

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transactions: PokemonDTO)

    @Query("SELECT * FROM `$TABLE_TRANSACTIONS`")
    fun load(): LiveData<List<PokemonDTO>>

    @Query("DELETE FROM `$TABLE_TRANSACTIONS`")
    fun deleteAll()
}