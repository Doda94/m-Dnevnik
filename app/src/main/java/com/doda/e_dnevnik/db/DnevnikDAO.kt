package com.doda.e_dnevnik.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DnevnikDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRazred(razred: RazredEntity)

    @Query("SELECT * FROM razredi where id is :id")
    fun getRazred(id: String): RazredEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOcjene(ocjene: OcjeneEntity)

    @Query("SELECT * FROM ocjene")
    fun getOcjene(): List<OcjeneEntity>

}