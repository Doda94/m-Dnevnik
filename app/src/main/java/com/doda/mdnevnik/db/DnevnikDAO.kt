package com.doda.mdnevnik.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doda.mdnevnik.Ocjena

@Dao
interface DnevnikDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPredmeti(predmeti: PredmetEntity)

    @Query("UPDATE predmeti SET prosjek = :prosjek WHERE predmetId IS :predmetId")
    fun updatePredmeti(predmetId: String, prosjek: Double)

    @Query("SELECT * FROM predmeti")
    fun getAllPredmeti(): List<PredmetEntity>

    @Query("UPDATE predmeti SET ocjene = :ocjene WHERE predmetId IS :predmetId")
    fun updateOcjene(predmetId: String, ocjene: List<Ocjena>?)
}