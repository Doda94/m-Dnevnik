package com.doda.e_dnevnik.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.e_dnevnik.Biljeska
import com.doda.e_dnevnik.razredi.Predmet
import com.doda.e_dnevnik.razredi.PredmetEntity

@Entity(tableName = "razredi")
data class RazredEntity(
    @PrimaryKey val id: String,
    val razred: String,
    val predmeti: List<PredmetEntity>,
    val biljeske: List<Biljeska>,
)
