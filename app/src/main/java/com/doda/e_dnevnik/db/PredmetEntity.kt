package com.doda.e_dnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.e_dnevnik.Ocjena
import com.doda.e_dnevnik.razredi.Predmet

@Entity(tableName = "predmeti")
data class PredmetEntity(
    @PrimaryKey val predmetId: String,
    val predmet: Predmet,
    val prvoPolugodiste: String?,
    val drugoPolugodiste: String?,
    val elementiOcjenjivanjaList: List<String>?,
    val ocjene: List<Ocjena>?,
    val prosjek: Double?,
)
