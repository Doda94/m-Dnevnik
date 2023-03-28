package com.doda.mdnevnik.izostanci

import androidx.lifecycle.ViewModel

class IzostanciViewModelFactory(
    val database: com.doda.mdnevnik.db.DnevnikDatabase
): androidx.lifecycle.ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IzostanciViewModel::class.java)) {
            return IzostanciViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}