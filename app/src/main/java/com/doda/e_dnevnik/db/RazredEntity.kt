package com.doda.e_dnevnik.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.e_dnevnik.razredi.Predmet
import com.doda.e_dnevnik.razredi.PredmetEntity

@Entity(tableName = "razredi")
data class RazredEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "razred") val razred: String,
    @ColumnInfo(name = "predmeti") val predmeti: List<PredmetEntity>,
)
