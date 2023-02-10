package com.doda.mdnevnik.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doda.mdnevnik.ispiti.Ispit

@Entity(tableName = "ispiti")
data class IspitiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ispitInfo: Ispit,
)
