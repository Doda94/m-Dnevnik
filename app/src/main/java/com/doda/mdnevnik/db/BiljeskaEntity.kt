package com.doda.mdnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.mdnevnik.biljeske.LinksData

@Entity(tableName = "biljeske")
data class BiljeskaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val dataText: String,
//    val linksData: LinksData,
)
