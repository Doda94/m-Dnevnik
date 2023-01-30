package com.doda.e_dnevnik.razredi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.e_dnevnik.db.DnevnikDatabase

class PredmetiViewModelFactory(
    val database: DnevnikDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PredmetiViewModel::class.java)) {
            return PredmetiViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}