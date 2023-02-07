package com.doda.mdnevnik.biljeske

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.db.BiljeskaEntity
import com.doda.mdnevnik.db.DnevnikDatabase
import java.util.concurrent.Executors

class BiljeskeViewModel(
    private val database: DnevnikDatabase
): ViewModel() {

    private var _biljeskeLiveData = MutableLiveData<List<BiljeskaEntity>>(listOf())

    val biljeskeLiveData: MutableLiveData<List<BiljeskaEntity>> = _biljeskeLiveData

    fun loadBiljeske() {
        Executors.newSingleThreadExecutor().execute {
            _biljeskeLiveData.postValue(database.dnevnikDAO().getAllBiljeske())
        }
    }
}