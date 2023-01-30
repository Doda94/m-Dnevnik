package com.doda.e_dnevnik.db

import androidx.room.TypeConverter
import com.doda.e_dnevnik.Biljeska
import com.doda.e_dnevnik.OcjeneResponse
import com.doda.e_dnevnik.razredi.PredmetEntity
import com.google.gson.Gson

class Converters {

    val gson = Gson()

    @TypeConverter
    fun listOfPredmetEntityToString(list: List<PredmetEntity>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToListOfPredmetEntity(string: String): List<PredmetEntity>{
        val objectType = object : com.google.gson.reflect.TypeToken<List<PredmetEntity>>() {}.type
        return gson.fromJson(string, objectType)
    }

    @TypeConverter
    fun listOfBiljeskaToString(list: List<Biljeska>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToListOfBiljeska(string: String): List<Biljeska>{
        val objectType = object : com.google.gson.reflect.TypeToken<List<Biljeska>>() {}.type
        return gson.fromJson(string, objectType)
    }

    @TypeConverter
    fun ocjeneResponseToString(ocjene: OcjeneResponse): String {
        return gson.toJson(ocjene)
    }

    @TypeConverter
    fun stringToOcjeneResponse(string: String): OcjeneResponse {
        return gson.fromJson(string, OcjeneResponse::class.java)
    }


}