package com.example.data.models

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromSpecies(species: Species): String{
        return species.name
    }
    @TypeConverter
    fun toSpecies(name: String): Species{
        return Species(name)
    }
    @TypeConverter
    fun fromSprites(sprites: Sprites): String? {
        return sprites.front_default
    }
    @TypeConverter
    fun toSprites(name: String?): Sprites{
        return Sprites(front_default = name)
    }
    @TypeConverter
    fun fromPokemonDTO(pokemonDTO: PokemonDTO): String{
        return pokemonDTO.name
    }
    @TypeConverter
    fun toPokemonDTO(name: String): PokemonDTO{
        return PokemonDTO(name = name)
    }
}