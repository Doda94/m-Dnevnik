package com.doda.mdnevnik.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        PredmetEntity::class,
        BiljeskaEntity::class,
        VladanjeEntity::class,
        IspitiEntity::class,
        IzostanakEntity::class,
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class DnevnikDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: DnevnikDatabase? = null

        fun getDatabase(context: Context): DnevnikDatabase {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(context, DnevnikDatabase::class.java, "dnevnik_db")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = database
                database
            }
        }
    }

    abstract fun dnevnikDAO(): DnevnikDAO

}