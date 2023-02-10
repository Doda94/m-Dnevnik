package com.doda.mdnevnik.ispiti

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doda.mdnevnik.db.DnevnikDatabase

class IspitiViewModelFactory(
    val database: DnevnikDatabase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IspitiViewModel::class.java)) {
            return IspitiViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}
