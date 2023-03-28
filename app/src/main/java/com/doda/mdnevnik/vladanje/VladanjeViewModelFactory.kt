package com.doda.mdnevnik.vladanje

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.mdnevnik.db.DnevnikDatabase

class VladanjeViewModelFactory(
    val database: DnevnikDatabase
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VladanjeViewModel::class.java)) {
            return VladanjeViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}