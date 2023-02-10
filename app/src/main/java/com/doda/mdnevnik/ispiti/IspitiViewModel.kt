package com.doda.mdnevnik.ispiti

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.db.DnevnikDatabase
import com.doda.mdnevnik.db.IspitiEntity
import java.util.concurrent.Executors

class IspitiViewModel(
    private val database: DnevnikDatabase
) : ViewModel() {

    private var _ispitiLiveData = MutableLiveData<List<IspitiEntity>>(listOf())

    val ispitiLiveData: MutableLiveData<List<IspitiEntity>> = _ispitiLiveData

    fun loadIspiti() {
        Executors.newSingleThreadExecutor().execute {
            _ispitiLiveData.postValue(database.dnevnikDAO().getAllIspiti())
        }
    }
}