package com.doda.e_dnevnik.api

import com.doda.e_dnevnik.preferences.MyPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TOKEN = "TOKEN"
const val EDUOTOKEN = "eduoToken"

object ApiModule {

    private const val BASE_URL = "https://eduo-ocjene.fly.dev/"

    lateinit var retrofit: EdnevnikApiService

    private val json = Json { ignoreUnknownKeys = true }

    fun initRetrofit(sharedPreferences: MyPreferences) {
        val okhttp = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
            .addInterceptor(AuthInterceptor(sharedPreferences))
            .build()

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okhttp).build().create(EdnevnikApiService::class.java)
    }

}

class AuthInterceptor (private val sharedPreferences: MyPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val token = sharedPreferences.getToken()
        var request = chain.request()
        request = request.newBuilder()
            .header(EDUOTOKEN, token.toString())
            .build()

        return chain.proceed(request)
    }
}



