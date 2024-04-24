package com.saoke.joyreader.api

import com.saoke.joyreader.logic.model.BlogListModel
import com.saoke.joyreader.logic.model.BlogModel
import com.saoke.joyreader.logic.model.LoginModel
import com.saoke.joyreader.logic.model.Model
import com.saoke.joyreader.logic.model.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    /// 登录
    @POST("/user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Model<LoginModel>>

    @GET("/user/get_user")
    fun getUser(): Call<Model<UserModel>>

    /// 获取首页文章列表
    @GET("/article/get_list")
    fun getBlogList(
        @Query("page") page: Int,
        @Query("sort") sort: Int
    ): Call<Model<BlogListModel>>

    /// 获取文章
    @GET("/article/get_article")
    fun getBlog(@Query("articleId") blogId: String): Call<Model<BlogModel>>
}
