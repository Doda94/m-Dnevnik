package com.doda.e_dnevnik.db

import androidx.room.TypeConverter
import com.doda.e_dnevnik.Biljeska
import com.doda.e_dnevnik.Ocjena
import com.doda.e_dnevnik.OcjeneResponse
import com.doda.e_dnevnik.razredi.Predmet
import com.google.gson.Gson

class Converters {

    val gson = Gson()

    @TypeConverter
    fun fromPredmetToString(predmet: Predmet): String {
        return gson.toJson(predmet)
    }

    @TypeConverter
    fun fromStringToPredmet(predmet: String): Predmet {
        return gson.fromJson(predmet, Predmet::class.java)
    }

    @TypeConverter
    fun fromListStringToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListString(list: String): List<String> {
        return gson.fromJson(list, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromListOcjenaToString(list: List<Ocjena>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListOcjena(list: String): List<Ocjena> {
        return gson.fromJson(list, Array<Ocjena>::class.java).toList()
    }

}