package com.doda.e_dnevnik.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.doda.e_dnevnik.OcjeneResponse

@Database(
    entities = [
        PredmetEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DnevnikDatabase: RoomDatabase() {

    companion object{

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