package com.doda.mdnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.mdnevnik.izostanci.Izostanak

@Entity(tableName = "izostanci")
data class IzostanakEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val izostanakInfo: Izostanak,
)
