package com.doda.e_dnevnik

import android.app.Application
import com.doda.e_dnevnik.db.DnevnikDatabase
import com.google.android.material.color.DynamicColors

class EdnevnikApplication : Application() {

    val database by lazy {
        DnevnikDatabase.getDatabase(this)
    }
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}