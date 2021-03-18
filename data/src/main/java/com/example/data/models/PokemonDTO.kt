package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.commons.Constants

@Entity(tableName = Constants.TABLE_TRANSACTIONS)

data class PokemonDTO (
    val baseExperience: Long? = null,
    val height: Long? = null,
    @PrimaryKey val id: Long? = null,
    val isDefault: Boolean? = null,
    val locationAreaEncounters: String? = null,
    val name: String,
    val order: Long? = null,
    val species: Species? = null,
    val sprites: Sprites? = null,
    val weight: Long?= null
)

data class Species (
    val name: String,
    val url: String? = null
)

data class Sprites (
    val backDefault: String? = null,
    val backFemale: String? = null,
    val backShiny: String? = null,
    val backShinyFemale: String? = null,
    val frontDefault: String? = null,
    val frontFemale: String? = null,
    val frontShiny: String? = null,
    val frontShinyFemale: String? = null,

)


