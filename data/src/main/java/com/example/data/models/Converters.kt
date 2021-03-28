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
    fun toListStats(data: String?): List<Stats>? {
        if (data == null) {
            return emptyList()
        }
        val listType: java.lang.reflect.Type? = object : TypeToken<List<Stats>?>() {}.type

        return gson.fromJson(data, listType)
    }


    @TypeConverter
    fun fromListStats(someObjects: List<Stats>?): String? {
        return gson.toJson(someObjects)
    }
}