package com.doda.e_dnevnik.razredi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.e_dnevnik.OcjeneResponse
import com.doda.e_dnevnik.api.ApiModule

class PredmetiViewModel : ViewModel() {

    private var _predmetiLiveData = MutableLiveData<List<Predmet>>()

    val predmetiLiveData : MutableLiveData<List<Predmet>> = _predmetiLiveData

    private var predmeti: List<Predmet> = listOf()

    fun loadPredmete(classId: String){
        ApiModule.retrofit.predmeti(classId).enqueue(object : retrofit2.Callback<PredmetiResponse> {
            override fun onResponse(call: retrofit2.Call<PredmetiResponse>, response: retrofit2.Response<PredmetiResponse>) {
                predmeti = response.body()?.data!!
                for (predmet in predmeti){
                    getOcjene(predmet)
                }

            }

            override fun onFailure(call: retrofit2.Call<PredmetiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getOcjene(predmet: Predmet) {
        ApiModule.retrofit.ocjene(predmet.subjectID).enqueue(object : retrofit2.Callback<OcjeneResponse> {
            override fun onResponse(call: retrofit2.Call<OcjeneResponse>, response: retrofit2.Response<OcjeneResponse>) {
                val ocjene = response.body()?.data!!
            }

            override fun onFailure(call: retrofit2.Call<OcjeneResponse>, t: Throwable) {
                TODO()
            }

        })
    }

}