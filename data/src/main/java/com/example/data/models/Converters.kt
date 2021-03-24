package com.example.data.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    private var gson = Gson()

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
    @TypeConverter
    fun fromType(type: Type): String{
        return type.name
    }
    @TypeConverter
    fun toType(name: String): Type{
        return Type(name)
    }

    @TypeConverter
    fun toListTypes(data: String?): List<Types>? {
        if (data == null) {
            return emptyList()
        }
        val listType: java.lang.reflect.Type? = object : TypeToken<List<Types>?>() {}.type

        return gson.fromJson(data, listType)
    }


    @TypeConverter
    fun fromListTypes(someObjects: List<Types>?): String? {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun fromTypes(types: Types): Long{
        return types.slot
    }
    @TypeConverter
    fun toTypes(slot: Long): Types{
        return Types(slot)
    }
}