package com.doda.e_dnevnik.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiModule {

    private const val BASE_URL = "https://ocjene.eduo.help/"

    lateinit var retrofit: EdnevnikApiService

    private val json = Json { ignoreUnknownKeys = true }

    fun initRetrofit() {
        val okhttp = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okhttp).build().create(EdnevnikApiService::class.java)
    }

}