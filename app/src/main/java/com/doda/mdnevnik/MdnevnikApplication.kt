package com.doda.mdnevnik

import android.app.Application
import com.doda.mdnevnik.db.DnevnikDatabase
import com.google.android.material.color.DynamicColors

class MdnevnikApplication : Application() {

    val database by lazy {
        DnevnikDatabase.getDatabase(this)
    }
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}