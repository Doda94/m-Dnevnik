package com.doda.e_dnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.e_dnevnik.OcjeneResponse

@Entity(tableName = "ocjene")
data class OcjeneEntity(
    @PrimaryKey val id: String,
    val ocjene: OcjeneResponse?,
    val prosjek: String,
)
