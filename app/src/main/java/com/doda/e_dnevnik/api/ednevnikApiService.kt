package com.doda.e_dnevnik.api

import com.doda.e_dnevnik.api.login.LoginRequest
import com.doda.e_dnevnik.api.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ednevnikApiService {

    @GET
    fun login (@Body request: LoginRequest): Call<LoginResponse>

}