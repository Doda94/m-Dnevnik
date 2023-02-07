package com.doda.mdnevnik.razredi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.mdnevnik.db.DnevnikDatabase

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