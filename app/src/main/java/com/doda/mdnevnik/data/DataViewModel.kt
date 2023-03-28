package com.doda.mdnevnik.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.api.ApiModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataViewModel : ViewModel() {

    private var _razrediLiveData = MutableLiveData<Array<Razred>>()

    val razrediLiveData : LiveData<Array<Razred>> = _razrediLiveData

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
