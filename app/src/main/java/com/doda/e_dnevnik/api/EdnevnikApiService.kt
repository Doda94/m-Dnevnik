package com.doda.e_dnevnik.api

import com.doda.e_dnevnik.data.DataResponse
import com.doda.e_dnevnik.login.LoginRequest
import com.doda.e_dnevnik.login.LoginResponse
import com.doda.e_dnevnik.razredi.PredmetiResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EdnevnikApiService {

    @POST("/api/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/api/scrape/userInfo")
    fun razredi(): Call<DataResponse>

    @GET("https://ocjene.eduo.help/api/scrape/class/{classID}")
    fun predmeti(@Path("classID") classID: String): Call<PredmetiResponse>

}




