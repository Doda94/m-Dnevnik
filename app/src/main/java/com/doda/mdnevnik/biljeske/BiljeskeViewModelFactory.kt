package com.doda.mdnevnik.biljeske

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.mdnevnik.db.DnevnikDatabase

class BiljeskeViewModelFactory(
    val database: DnevnikDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BiljeskeViewModel::class.java)) {
            return BiljeskeViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}