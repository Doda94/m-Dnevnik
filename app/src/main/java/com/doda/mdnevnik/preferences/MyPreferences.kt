package com.doda.mdnevnik.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

const val EDNEVNIK_SHARED_PREFS = "EDNEVNIK_SHARED_PREFERENCES"
const val TOKEN = "TOKEN"

class MyPreferences(
    context: Context
) {

    private val appContext = context.applicationContext
    private val sharedPreferences: SharedPreferences = appContext.getSharedPreferences(EDNEVNIK_SHARED_PREFS, Context.MODE_PRIVATE)

    fun putToken(userToken: String) {
        sharedPreferences.edit {
            putString(TOKEN, userToken)
        }
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, null)
    }

}