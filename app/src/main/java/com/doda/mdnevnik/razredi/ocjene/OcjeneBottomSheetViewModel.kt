package com.doda.mdnevnik.razredi.ocjene

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.db.DnevnikDatabase
import com.doda.mdnevnik.db.PredmetEntity
import com.doda.mdnevnik.razredi.Rubrika
import java.util.concurrent.Executors

class OcjeneBottomSheetViewModel(
    private val database: DnevnikDatabase
) : ViewModel() {

    private var _ocjeneLiveData = MutableLiveData<List<Rubrika>>(listOf())

    val ocjeneLiveData: MutableLiveData<List<Rubrika>> = _ocjeneLiveData

    fun loadOcjene(classId: String) {
        var ocjene: List<Ocjena>? = null
        var predmet: PredmetEntity? = null
        Executors.newSingleThreadExecutor().execute {
            predmet = database.dnevnikDAO().getPredmet(classId)
            if (predmet != null) {
                ocjene = predmet!!.ocjene
            }
            var rubrike: List<Rubrika> = listOf()

            var ocjeneRubrika: MutableMap<String, List<Ocjena>> = mutableMapOf()

            if (ocjene != null) {
                for (ocjena in ocjene!!) {
                    val rubrika = ocjena.field
                    if (ocjeneRubrika[rubrika] == null) {
                        ocjeneRubrika[rubrika] = listOf(ocjena)
                    } else {
                        ocjeneRubrika[rubrika] = ocjeneRubrika[rubrika]!!.plus(ocjena)
                    }
                }
                ocjeneRubrika.forEach() { (key, value) ->
                    val listaOcjena = value.sortedBy { it.date }
                    rubrike = rubrike.plus(Rubrika(key, listaOcjena))
                }
            }
            rubrike = rubrike.sortedBy { it.name }
            _ocjeneLiveData.postValue(rubrike)
        }
    }
}