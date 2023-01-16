package com.doda.e_dnevnik.razredi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.e_dnevnik.api.ApiModule

class PredmetiViewModel : ViewModel() {

    private var _predmetiLiveData = MutableLiveData<List<Predmet>>()

    val predmetiLiveData : MutableLiveData<List<Predmet>> = _predmetiLiveData

    fun loadPredmete(classId: String){
        ApiModule.retrofit.predmeti(classId).enqueue(object : retrofit2.Callback<PredmetiResponse> {
            override fun onResponse(call: retrofit2.Call<PredmetiResponse>, response: retrofit2.Response<PredmetiResponse>) {
                _predmetiLiveData.value = response.body()?.data
            }

            override fun onFailure(call: retrofit2.Call<PredmetiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}