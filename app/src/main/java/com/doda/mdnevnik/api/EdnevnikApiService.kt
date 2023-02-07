package com.doda.mdnevnik.api

import com.doda.mdnevnik.OcjeneResponse
import com.doda.mdnevnik.data.DataResponse
import com.doda.mdnevnik.login.LoginRequest
import com.doda.mdnevnik.login.LoginResponse
import com.doda.mdnevnik.razredi.PredmetiResponse
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

    @GET("https://eduo-ocjene.fly.dev/api/scrape/class/{classID}")
    fun predmeti(@Path("classID") classID: String): Call<PredmetiResponse>

    @GET("https://eduo-ocjene.fly.dev/api/scrape/subject/{subjectID}")
    fun ocjene(@Path("subjectID") subjectID: String): Call<OcjeneResponse>

}




