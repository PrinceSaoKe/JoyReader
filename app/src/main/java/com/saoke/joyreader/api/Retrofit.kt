package com.saoke.joyreader.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {
    companion object {
        private const val baseUrl = "http://120.24.176.40:8080"

        private val okHttp: OkHttpClient =
            OkHttpClient.Builder().addInterceptor(Interceptor()).build()

        val api: Api by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(Api::class.java)
        }
    }
}