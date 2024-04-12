package com.saoke.joyreader.api

import android.util.Log
import com.tencent.mmkv.MMKV
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor : Interceptor {
    private val mmkv = MMKV.defaultMMKV()

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = mmkv.decodeString("token", null)
        val request: Request =
            if (token == null) chain.request()
            else chain.request().newBuilder().header("Authorization", token).build()
        val response = chain.proceed(request)
        if (response.code == 401) {
//            val intent = Intent(context, AuthActivity::class.java)
            Log.i("MyLog", "Token失效")
        }
        return response
    }
}