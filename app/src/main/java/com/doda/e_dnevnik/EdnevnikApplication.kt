package com.doda.e_dnevnik

import android.app.Application
import com.google.android.material.color.DynamicColors

class EdnevnikApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}