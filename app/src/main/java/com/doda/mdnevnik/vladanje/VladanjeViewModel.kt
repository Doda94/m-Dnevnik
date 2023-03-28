package com.doda.mdnevnik.vladanje

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.db.DnevnikDatabase
import com.doda.mdnevnik.db.VladanjeEntity
import java.util.concurrent.Executors

class VladanjeViewModel(
    private val database: DnevnikDatabase
): ViewModel() {

    private var _vladanjeLiveData = MutableLiveData<List<VladanjeEntity>>(emptyList())

    val vladanjeLiveData get() = _vladanjeLiveData

    fun getVladanje() {
        Executors.newSingleThreadExecutor().execute {
            _vladanjeLiveData.postValue(database.dnevnikDAO().getAllVladanje())
        }
    }
}