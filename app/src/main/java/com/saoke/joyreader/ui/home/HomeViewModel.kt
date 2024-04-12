package com.saoke.joyreader.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.logic.model.BlogListModel
import com.saoke.joyreader.logic.model.BlogModel
import com.saoke.joyreader.logic.model.Model
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _tabs: List<String> = listOf("热门", "最新")
    val tabs: List<String> = _tabs

    val hotBlogList = MutableLiveData<List<BlogModel>>(listOf())

    val latestBlogList = MutableLiveData<List<BlogModel>>(listOf())

    private var currPage = 1

    fun getBlogList() {
        // 获取热门列表
        Retrofit.api.getBlogList(currPage, 0)
            .enqueue(object : Callback<Model<BlogListModel>> {
                override fun onResponse(
                    call: Call<Model<BlogListModel>>,
                    response: Response<Model<BlogListModel>>
                ) {
                    if (response.isSuccessful) {
                        hotBlogList.value = response.body()!!.data.articles
                    } else {
                        Log.i("MyLog", "getHotBlogList：${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Model<BlogListModel>>, t: Throwable) {
                    Log.i("MyLog", "请求失败: ${t.message}")
                }
            })

        // 获取最新列表
        Retrofit.api.getBlogList(currPage, 1)
            .enqueue(object : Callback<Model<BlogListModel>> {
                override fun onResponse(
                    call: Call<Model<BlogListModel>>,
                    response: Response<Model<BlogListModel>>
                ) {
                    if (response.isSuccessful) {
                        latestBlogList.value = response.body()!!.data.articles
                    } else {
                        Log.i("MyLog", "getLatestBlogList：${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Model<BlogListModel>>, t: Throwable) {
                    Log.i("MyLog", "请求失败: ${t.message}")
                }
            })
    }
}