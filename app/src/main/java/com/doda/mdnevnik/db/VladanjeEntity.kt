package com.doda.mdnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "vladanje")
data class VladanjeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val dataText: String,

)
