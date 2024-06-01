package com.saoke.joyreader.ui.blog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.logic.model.BlogModel
import com.saoke.joyreader.logic.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogViewModel : ViewModel() {
    val blogModel = MutableLiveData<BlogModel?>()

    fun getBlog(blogId: String) {
        Retrofit.api.getBlog(blogId).enqueue(object : Callback<Model<BlogModel>> {
            override fun onResponse(
                call: Call<Model<BlogModel>>,
                response: Response<Model<BlogModel>>
            ) {
                if (response.isSuccessful) {
                    blogModel.value = response.body()?.data
                } else {
                    Log.i("JoyReader", "getBlog：${response.code()}")
                }
            }

            override fun onFailure(call: Call<Model<BlogModel>>, t: Throwable) {
                Log.i("JoyReader", "getBlog请求失败: ${t.message}")
            }
        })
    }
}