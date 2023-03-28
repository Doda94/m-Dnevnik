package com.doda.mdnevnik.db

import androidx.room.TypeConverter
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.OcjeneResponse
import com.doda.mdnevnik.biljeske.LinksData
import com.doda.mdnevnik.ispiti.Ispit
import com.doda.mdnevnik.izostanci.Izostanak
import com.doda.mdnevnik.razredi.Predmet
import com.google.gson.Gson

class Converters {

    private val gson = Gson()

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

    @TypeConverter
    fun fromLinksDataToString(linksData: LinksData): String {
        return gson.toJson(linksData)
    }

    @TypeConverter
    fun fromStringToLinksData(linksData: String): LinksData {
        return gson.fromJson(linksData, LinksData::class.java)
    }

    @TypeConverter
    fun fromListOcjeneEntityToString(list: List<OcjeneEntity>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToListOcjeneEntity(list: String): List<OcjeneEntity> {
        return gson.fromJson(list, Array<OcjeneEntity>::class.java).toList()
    }

    @TypeConverter
    fun fromOcjeneEntityToString(ocjeneEntity: OcjeneEntity): String {
        return gson.toJson(ocjeneEntity)
    }

    @TypeConverter
    fun fromStringToOcjeneEntity(ocjeneEntity: String): OcjeneEntity {
        return gson.fromJson(ocjeneEntity, OcjeneEntity::class.java)
    }

    @TypeConverter
    fun fromOcjeneResponseToString(ocjeneResponse: OcjeneResponse): String {
        return gson.toJson(ocjeneResponse)
    }

    @TypeConverter
    fun fromStringToOcjeneResponse(ocjeneResponse: String): OcjeneResponse {
        return gson.fromJson(ocjeneResponse, OcjeneResponse::class.java)
    }

    @TypeConverter
    fun fromIspitToString(ispit: Ispit): String {
        return gson.toJson(ispit)
    }

    @TypeConverter
    fun fromStringToIspit(ispit: String): Ispit {
        return gson.fromJson(ispit, Ispit::class.java)
    }

    @TypeConverter
    fun fromIzostanakToString(izostanak: Izostanak): String {
        return gson.toJson(izostanak)
    }

    @TypeConverter
    fun fromStringToIzostanak(izostanak: String): Izostanak {
        return gson.fromJson(izostanak, Izostanak::class.java)
    }

}