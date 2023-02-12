package com.doda.mdnevnik.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doda.mdnevnik.api.ApiModule
import com.doda.mdnevnik.preferences.MyPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val loginResultLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }

    fun getLoginResultLiveData(): LiveData<Int> {
        return loginResultLiveData
    }

    fun onLoginButtonClicked(email: String, password: String, sharedPreferences: MyPreferences) {
        val request = LoginRequest(email, password)
        ApiModule.retrofit.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body()!!.isSuccessful) {
                    loginResultLiveData.value = 1
                    sharedPreferences.putToken(response.body()!!.token)
                } else {
                    loginResultLiveData.value = 0
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResultLiveData.value = 2
            }

        })
    }
}