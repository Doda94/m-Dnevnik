package com.doda.mdnevnik.razredi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.OcjeneResponse
import com.doda.mdnevnik.api.ApiModule
import com.doda.mdnevnik.biljeske.Biljeska
import com.doda.mdnevnik.biljeske.BiljeskeResponse
import com.doda.mdnevnik.db.BiljeskaEntity
import com.doda.mdnevnik.db.DnevnikDatabase
import com.doda.mdnevnik.db.PredmetEntity
import com.doda.mdnevnik.db.VladanjeEntity
import com.doda.mdnevnik.vladanje.VladanjeResponse
import java.util.concurrent.Executors
import retrofit2.Call
import retrofit2.Response

class PredmetiViewModel(
    private val database: DnevnikDatabase
) : ViewModel() {

    private var _predmetiLiveData = MutableLiveData<List<PredmetEntity>>()

    val predmetiLiveData: MutableLiveData<List<PredmetEntity>> = _predmetiLiveData

    private var _numberOfProsjekLiveData = MutableLiveData(0)

    val numberOfProsjekLiveData: MutableLiveData<Int> = _numberOfProsjekLiveData

    private var predmeti: List<Predmet> = listOf()

    fun clearDB() {
        Executors.newSingleThreadExecutor().execute {
            database.clearAllTables()
        }
    }

    fun loadVladanje(classId: String){
        ApiModule.retrofit.vladanje(classId).enqueue(object : retrofit2.Callback<VladanjeResponse> {
            override fun onResponse(call: Call<VladanjeResponse>, response: Response<VladanjeResponse>) {
                var vladanja = response.body()?.data!!
                for (vladanje in vladanja){
                    Executors.newSingleThreadExecutor().execute {
                        database.dnevnikDAO().insertVladanje(
                            VladanjeEntity(
                                title = vladanje.title,
                                dataText = vladanje.dataText,
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<VladanjeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadBiljeske(classId: String) {
        ApiModule.retrofit.biljeske(classId).enqueue(object : retrofit2.Callback<BiljeskeResponse> {
            override fun onResponse(call: Call<BiljeskeResponse>, response: Response<BiljeskeResponse>) {
                var biljeske = response.body()?.data!!
                for (biljeska in biljeske) {
                    Executors.newSingleThreadExecutor().execute {
                        database.dnevnikDAO().insertBiljeske(
                            BiljeskaEntity(
                                title = biljeska.title,
                                dataText = biljeska.dataText,
//                                linksData = biljeska.linksData
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<BiljeskeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun loadPredmete(classId: String) {
        ApiModule.retrofit.predmeti(classId).enqueue(object : retrofit2.Callback<PredmetiResponse> {
            override fun onResponse(call: retrofit2.Call<PredmetiResponse>, response: retrofit2.Response<PredmetiResponse>) {
                predmeti = response.body()?.data!!
                for (predmet in predmeti) {
                    val ocjene = getOcjene(predmet)
                    var prosjek = 0.0
                    val currentPredmet = PredmetEntity(
                        predmet.subjectID,
                        predmet,
                        ocjene.prvoPolugodiste,
                        ocjene.drugoPolugodiste,
                        ocjene.elementiOcjenjivanjaList,
                        ocjene.data,
                        prosjek,
                    )
                    Executors.newSingleThreadExecutor().execute {
                        database.dnevnikDAO().insertPredmeti(currentPredmet)
                    }
                }
                Executors.newSingleThreadExecutor().execute {
                    _predmetiLiveData.postValue(database.dnevnikDAO().getAllPredmeti())
                }
            }

            override fun onFailure(call: retrofit2.Call<PredmetiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getOcjene(predmet: Predmet): OcjeneResponse {
        var ocjene = OcjeneResponse("", "", listOf(), listOf())
        ApiModule.retrofit.ocjene(predmet.subjectID).enqueue(object : retrofit2.Callback<OcjeneResponse> {
            override fun onResponse(call: retrofit2.Call<OcjeneResponse>, response: retrofit2.Response<OcjeneResponse>) {
                ocjene = response.body()!!
                var prosjek = 0.0
                var brocjena = 0
                if (ocjene.data != null) {
                    for (ocjena in ocjene.data!!) {
                        if (ocjena.grade != "") {
                            prosjek += ocjena.grade.toDouble()
                            brocjena++
                        }
                    }
                    prosjek /= brocjena.toDouble()
                }
                Executors.newSingleThreadExecutor().execute {
                    database.dnevnikDAO().updatePredmeti(predmet.subjectID, prosjek)
                    database.dnevnikDAO().updateOcjene(predmet.subjectID, ocjene.data)
                    _predmetiLiveData.postValue(database.dnevnikDAO().getAllPredmeti())
                }
                _numberOfProsjekLiveData.value = _numberOfProsjekLiveData.value?.plus(1)
            }

            override fun onFailure(call: retrofit2.Call<OcjeneResponse>, t: Throwable) {
                TODO()
            }
        })
        return ocjene
    }

}