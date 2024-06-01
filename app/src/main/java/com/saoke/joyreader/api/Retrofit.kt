package com.saoke.joyreader.api

import android.util.Log
import com.saoke.joyreader.logic.model.Model
import com.saoke.joyreader.logic.model.UserModel
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        fun getUser() {
            api.getUser().enqueue(object : Callback<Model<UserModel>> {
                override fun onResponse(
                    call: Call<Model<UserModel>>,
                    response: Response<Model<UserModel>>
                ) {
                    if (response.isSuccessful) {
                        val mmkv = MMKV.defaultMMKV()
                        val userId = response.body()?.data?.userId
                        val username = response.body()?.data?.username
                        val avatarUrl = response.body()?.data?.avatarUrl
                        if (userId != null) mmkv.encode("user_id", userId)
                        if (username != null) mmkv.encode("username", username)
                        if (avatarUrl != null) mmkv.encode("avatar_url", avatarUrl)
                        Log.i("JoyReader", "用户名：$username")
                        Log.i("JoyReader", "头像：$avatarUrl")
                    } else {
                        Log.i("JoyReader", "getUser：${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Model<UserModel>>, t: Throwable) {
                    Log.i("JoyReader", "请求失败: ${t.message}")
                }
            })
        }
    }
}