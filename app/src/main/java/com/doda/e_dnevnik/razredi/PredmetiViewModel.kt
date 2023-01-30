package com.doda.e_dnevnik.razredi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.e_dnevnik.Ocjena
import com.doda.e_dnevnik.OcjeneResponse
import com.doda.e_dnevnik.api.ApiModule
import com.doda.e_dnevnik.data.Razred
import com.doda.e_dnevnik.db.DnevnikDatabase
import com.doda.e_dnevnik.db.OcjeneEntity
import com.doda.e_dnevnik.db.RazredEntity
import java.util.concurrent.Executors

class PredmetiViewModel(
    private val database: DnevnikDatabase
) : ViewModel() {

    private var _predmetiLiveData = MutableLiveData<List<PredmetEntity>>()

    val predmetiLiveData: MutableLiveData<List<PredmetEntity>> = _predmetiLiveData

    private var predmeti: List<Predmet> = listOf()

    fun updateDbLiveData(classId: String) {
        _predmetiLiveData.value = database.dnevnikDAO().getRazred(classId).predmeti
    }

    fun loadPredmete(classId: String, razred: String) {
        ApiModule.retrofit.predmeti(classId).enqueue(object : retrofit2.Callback<PredmetiResponse> {
            override fun onResponse(call: retrofit2.Call<PredmetiResponse>, response: retrofit2.Response<PredmetiResponse>) {
                predmeti = response.body()?.data!!
                var predmetiZaDb: List<PredmetEntity> = listOf()
                for (predmet in predmeti) {
                    val ocjene = getOcjene(predmet)
                    var zbrojOcjena = 0
                    for (ocjena in ocjene.data!!) {
                        zbrojOcjena += ocjena.grade.toInt()
                    }
                    var prosjek: Double? = null
                    if (ocjene.data.isNotEmpty()) {
                        prosjek = zbrojOcjena / ocjene.data.size.toDouble()
                    }
                    val currentPredmet: PredmetEntity = PredmetEntity(
                        predmet.subjectID,
                        predmet,
                        ocjene.prvoPolugodiste,
                        ocjene.drugoPolugodiste,
                        ocjene.elementiOcjenjivanjaList,
                        ocjene.data,
                        prosjek,
                    )
                    predmetiZaDb += currentPredmet
                }
                Executors.newSingleThreadExecutor().execute {
                    database.dnevnikDAO().insertRazred(RazredEntity(classId, razred, predmetiZaDb, listOf()))
                }
            }

            override fun onFailure(call: retrofit2.Call<PredmetiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getOcjene(predmet: Predmet): OcjeneResponse {
        var ocjene: OcjeneResponse = OcjeneResponse("", "", listOf(), listOf())
        ApiModule.retrofit.ocjene(predmet.subjectID).enqueue(object : retrofit2.Callback<OcjeneResponse> {
            override fun onResponse(call: retrofit2.Call<OcjeneResponse>, response: retrofit2.Response<OcjeneResponse>) {
                ocjene = response.body()!!
                var prosjek = 0.0
                var brocjena = 0
                if (ocjene.data != null) {
                    for (ocjena in ocjene.data!!) {
                        if (ocjena.grade != ""){
                            prosjek += ocjena.grade.toDouble()
                            brocjena++
                        }
                    }
                    prosjek /= brocjena.toDouble()
                }
                Executors.newSingleThreadExecutor().execute {
//                    database.dnevnikDAO().updateProsjek(prosjek, predmet.subject)
//                    updateDbLiveData(predmet.subjectID)
                }
            }

            override fun onFailure(call: retrofit2.Call<OcjeneResponse>, t: Throwable) {
                TODO()
            }
        })
        return ocjene
    }

}