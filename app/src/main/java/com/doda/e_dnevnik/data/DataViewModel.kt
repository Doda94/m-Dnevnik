package com.doda.e_dnevnik.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.e_dnevnik.api.ApiModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {

    var razredi = arrayOf(Razred)

    private val _razrediLiveData = MutableLiveData<Array<Razred>>()

    fun getRazredi() : Array<Razred>? {
        return _razrediLiveData.value
    }

    fun loadRazrede() {
        ApiModule.retrofit.razredi().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                _razrediLiveData.value = response.body()?.data
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}
