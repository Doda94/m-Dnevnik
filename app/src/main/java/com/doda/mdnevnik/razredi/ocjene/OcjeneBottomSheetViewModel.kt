package com.doda.mdnevnik.razredi.ocjene

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.Ocjena
import com.doda.mdnevnik.db.DnevnikDatabase
import com.doda.mdnevnik.db.OcjeneEntity
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

            // TODO: ispod ovog izgubim neke ocjene

            var rubrike: List<Rubrika> = listOf()

            var ocjeneRubrika: MutableMap<String, List<Ocjena>> = mutableMapOf()

            if (ocjene != null) {
                for (ocjena in ocjene!!) {
                    var rubrika = ""
                    if (ocjena.field != null) {
                        rubrika = ocjena.field
                    }
                    if (ocjeneRubrika.containsKey(rubrika)) {
                        ocjeneRubrika[rubrika]!!.plus(ocjena)
                    } else {
                        ocjeneRubrika[rubrika] = listOf(ocjena)
                    }
                }

                ocjeneRubrika.forEach() { (key, value) ->
                    rubrike = rubrike.plus(Rubrika(key, value))
                }
            }
            _ocjeneLiveData.postValue(rubrike)
        }
    }
}