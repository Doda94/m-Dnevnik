package com.doda.e_dnevnik.api

import com.doda.e_dnevnik.login.LoginRequest
import com.doda.e_dnevnik.login.LoginResponse
import java.text.Normalizer.Form
import okhttp3.FormBody
import okhttp3.Request
import okhttp3.internal.http.hasBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST

interface EdnevnikApiService {

    @POST("/api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

}




