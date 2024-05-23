package com.saoke.joyreader.ui.my

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saoke.joyreader.api.Retrofit
import com.saoke.joyreader.logic.model.BlogListModel
import com.saoke.joyreader.logic.model.BlogModel
import com.saoke.joyreader.logic.model.Model
import com.tencent.mmkv.MMKV
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {

    val username = MutableLiveData("未命名")
    val userBlogList = MutableLiveData<List<BlogModel>>(listOf())
    val likeBlogList = MutableLiveData<List<BlogModel>>(listOf())

    init {
        val mmkv = MMKV.defaultMMKV()
        username.value = mmkv.decodeString("username", "未命名")!!
    }

//    // TODO 测试数据
//    private val _blogList: List<BlogModel> = listOf(
//        BlogModel(
//            "1",
//            "0",
//            "admin",
//            "",
//            "这是标题",
//            "这是描述",
//            "这是内容",
////            LocalDateTime.of(2024, 3, 24, 14, 48, 22),
//            "2024-03-24 14:48:22",
//            0,
//            0,
//        ),
//        BlogModel(
//            "1",
//            "0",
//            "骚客",
//            "",
//            "这是标题",
//            "这是描述",
//            "这是内容",
////            LocalDateTime.of(2024, 3, 24, 14, 48, 22),
//            "2024-03-24 14:48:22",
//            0,
//            0,
//        ),
//    )

    private val _tabs: List<String> = listOf("我发布的", "我赞过的")
    val tabs: List<String> = _tabs

//    private val _myPostBlogList: List<BlogModel> = _blogList
//    val myPostBlogList: List<BlogModel> = _myPostBlogList
//
//    private val _myLikeBlogList: List<BlogModel> = _blogList
//    val myLikeBlogList: List<BlogModel> = _myLikeBlogList

    fun getUserBlogList() {
        Retrofit.api.getUserBlogList().enqueue(object : Callback<Model<List<BlogModel>>> {
            override fun onResponse(
                call: Call<Model<List<BlogModel>>>,
                response: Response<Model<List<BlogModel>>>
            ) {
                if (response.isSuccessful) {
                    userBlogList.value = response.body()!!.data!!
                    Log.i("MyLog", userBlogList.value.toString())
                } else {
                    Log.i("MyLog", "getUserBlogList：${response.code()}")
                }
            }

            override fun onFailure(call: Call<Model<List<BlogModel>>>, t: Throwable) {
                Log.i("MyLog", "请求失败: ${t.message}")
            }
        })
    }

    fun getLikeBlogList() {
        Retrofit.api.getLikeBlogList().enqueue(object : Callback<Model<List<BlogModel>>> {
            override fun onResponse(
                call: Call<Model<List<BlogModel>>>,
                response: Response<Model<List<BlogModel>>>
            ) {
                if (response.isSuccessful) {
                    likeBlogList.value = response.body()!!.data!!
                    Log.i("MyLog", likeBlogList.value.toString())
                } else {
                    Log.i("MyLog", "getLikeBlogList：${response.code()}")
                }
            }

            override fun onFailure(call: Call<Model<List<BlogModel>>>, t: Throwable) {
                Log.i("MyLog", "请求失败: ${t.message}")
            }
        })
    }
}