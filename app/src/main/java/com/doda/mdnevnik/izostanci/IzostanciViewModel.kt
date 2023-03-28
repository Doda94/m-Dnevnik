package com.doda.mdnevnik.izostanci

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.db.DnevnikDatabase
import com.doda.mdnevnik.db.IzostanakEntity
import java.util.concurrent.Executors

class IzostanciViewModel(
    private val database: DnevnikDatabase
) : ViewModel() {

    private var _izostanciLiveData = MutableLiveData<List<DatumIzostanka>>(listOf())

    val izostanciLiveData: MutableLiveData<List<DatumIzostanka>> = _izostanciLiveData

    fun loadIzostanke() {
        var izostanci: List<IzostanakEntity>? = null
        var datumi: MutableMap<Long, List<IzostanakEntity>> = mutableMapOf()
        Executors.newSingleThreadExecutor().execute {
            izostanci = database.dnevnikDAO().getAllIzostanci()
            var datumIzostanci: List<DatumIzostanka> = listOf()
            if (izostanci != null) {
                for (izostanak in izostanci!!){
                    if (datumi[izostanak.izostanakInfo.date] == null){
                        datumi[izostanak.izostanakInfo.date] = listOf(izostanak)
                    } else {
                        datumi[izostanak.izostanakInfo.date] = datumi[izostanak.izostanakInfo.date]!!.plus(izostanak)
                    }
                }

                datumi.forEach() { (key, value) ->
                    datumIzostanci = datumIzostanci.plus(DatumIzostanka(key, value))
                }
            }
            _izostanciLiveData.postValue(datumIzostanci)
        }
    }
}