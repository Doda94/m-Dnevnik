package com.doda.mdnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.razredi.Predmet

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
