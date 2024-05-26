package com.saoke.joyreader.api

import com.saoke.joyreader.logic.model.BaseModel
import com.saoke.joyreader.logic.model.BlogListModel
import com.saoke.joyreader.logic.model.BlogModel
import com.saoke.joyreader.logic.model.LoginModel
import com.saoke.joyreader.logic.model.Model
import com.saoke.joyreader.logic.model.UserModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface Api {

    /// 登录
    @POST("/user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Model<LoginModel>>

    /// 注册
    @POST("/user/register")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<Model<BaseModel>>

    /// 查询用户信息
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

    /// 查询用户发布的文章列表
    @GET("/user/get_user_article")
    fun getUserBlogList(): Call<Model<List<BlogModel>>>

    /// 查询用户点赞的文章列表
    @GET("/user/get_user_likes")
    fun getLikeBlogList(): Call<Model<List<BlogModel>>>

    @POST("/user/update_avatar")
    @Multipart
    fun updateAvatar(@Part file: MultipartBody.Part): Call<Model<String>>
}
