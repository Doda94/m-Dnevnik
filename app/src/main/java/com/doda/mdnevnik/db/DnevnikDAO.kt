package com.doda.mdnevnik.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.OcjeneResponse

@Dao
interface DnevnikDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPredmeti(predmeti: PredmetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBiljeske(biljeske: BiljeskaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVladanje(vladanje: VladanjeEntity)

    @Query("UPDATE predmeti SET prosjek = :prosjek WHERE predmetId IS :predmetId")
    fun updatePredmeti(predmetId: String, prosjek: Double)

    @Query("SELECT * FROM predmeti")
    fun getAllPredmeti(): List<PredmetEntity>

    @Query("SELECT * FROM biljeske")
    fun getAllBiljeske(): List<BiljeskaEntity>

    @Query("SELECT * FROM vladanje")
    fun getAllVladanje(): List<VladanjeEntity>

    @Query("SELECT * FROM predmeti WHERE predmetId IS :predmetId")
    fun getPredmet(predmetId: String): PredmetEntity

    @Query("UPDATE predmeti SET ocjene = :ocjene WHERE predmetId IS :predmetId")
    fun updateOcjene(predmetId: String, ocjene: List<Ocjena>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIspiti(ispiti: IspitiEntity)

    @Query("SELECT * FROM ispiti")
    fun getAllIspiti(): List<IspitiEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIzostanci(izostanci: IzostanakEntity)

    @Query("SELECT * FROM izostanci")
    fun getAllIzostanci(): List<IzostanakEntity>

}