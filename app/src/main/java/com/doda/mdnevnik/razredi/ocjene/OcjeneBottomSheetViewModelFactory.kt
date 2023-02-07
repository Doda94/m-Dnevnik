package com.doda.mdnevnik.razredi.ocjene

import androidx.lifecycle.ViewModel

class OcjeneBottomSheetViewModelFactory(
    val database: com.doda.mdnevnik.db.DnevnikDatabase
) : androidx.lifecycle.ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OcjeneBottomSheetViewModel::class.java)) {
            return OcjeneBottomSheetViewModel(database) as T
        }
        throw IllegalArgumentException("")
    }
}
