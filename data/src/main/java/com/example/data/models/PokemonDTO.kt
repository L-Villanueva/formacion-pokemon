package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.commons.Constants

@Entity(tableName = Constants.TABLE_TRANSACTIONS)

data class PokemonDTO (
    val base_experience: Long? = null,
    val height: Long? = null,
    @PrimaryKey val id: Long? = null,
    val is_default: Boolean? = null,
    val location_area_encounters: String? = null,
    val name: String,
    val order: Long? = null,
    val species: Species? = null,
    val sprites: Sprites? = null,
    val weight: Long? = null,
    val types: List<Types>? = null
)

data class Species (
    val name: String,
    val url: String? = null
)

data class Sprites (
    val back_default: String? = null,
    val back_demale: String? = null,
    val back_shiny: String? = null,
    val back_shiny_female: String? = null,
    val front_default: String? = null,
    val front_female: String? = null,
    val front_shiny: String? = null,
    val front_shiny_female: String? = null,

)

data class Types (
        val slot: Long,
        val type: Type? = null
)

data class Type (
        val name: String,
        val url: String? = null
)

