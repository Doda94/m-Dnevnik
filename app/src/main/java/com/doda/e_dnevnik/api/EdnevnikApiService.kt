package com.doda.e_dnevnik.api

import com.doda.e_dnevnik.login.LoginRequest
import com.doda.e_dnevnik.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EdnevnikApiService {

    @POST("/api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}




