package com.doda.mdnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.mdnevnik.OcjeneResponse

@Entity(tableName = "ocjene")
data class OcjeneEntity(
    @PrimaryKey val id: String,
    val ocjene: OcjeneResponse?,
    val prosjek: String,
)
