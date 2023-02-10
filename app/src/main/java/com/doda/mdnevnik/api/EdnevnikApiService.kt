package com.doda.mdnevnik.api

import com.doda.mdnevnik.OcjeneResponse
import com.doda.mdnevnik.biljeske.BiljeskeResponse
import com.doda.mdnevnik.data.DataResponse
import com.doda.mdnevnik.ispiti.IspitiResponse
import com.doda.mdnevnik.izostanci.IzostanciResponse
import com.doda.mdnevnik.login.LoginRequest
import com.doda.mdnevnik.login.LoginResponse
import com.doda.mdnevnik.razredi.PredmetiResponse
import com.doda.mdnevnik.vladanje.VladanjeResponse
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

    @GET("/api/scrape/class/{classID}")
    fun predmeti(@Path("classID") classID: String): Call<PredmetiResponse>

    @GET("/api/scrape/subject/{subjectID}")
    fun ocjene(@Path("subjectID") subjectID: String): Call<OcjeneResponse>

    @GET("/api/scrape/notes/{classID}")
    fun biljeske(@Path("classID") classID: String): Call<BiljeskeResponse>

    @GET("/api/scrape/behavior/{classID}")
    fun vladanje(@Path("classID") classID: String): Call<VladanjeResponse>

    @GET("/api/scrape/tests/{classID}")
    fun ispiti(@Path("classID") classID: String): Call<IspitiResponse>

    @GET("/api/scrape/absent/{classID}")
    fun izostanci(@Path("classID") classID: String): Call<IzostanciResponse>

}




