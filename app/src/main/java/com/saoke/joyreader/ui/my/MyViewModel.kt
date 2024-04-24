package com.saoke.joyreader.ui.my

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saoke.joyreader.logic.model.BlogModel
import com.tencent.mmkv.MMKV

class MyViewModel : ViewModel() {

    val username = MutableLiveData("未命名")
    val avatarUrl =
        MutableLiveData("http://west2-work4-pany0593.oss-cn-shenzhen.aliyuncs.com/avatar/default-avatar.png")

    init {
        val mmkv = MMKV.defaultMMKV()
        username.value = mmkv.decodeString("username", "未命名")!!
        avatarUrl.value = mmkv.decodeString(
            "avatar_url",
            "http://west2-work4-pany0593.oss-cn-shenzhen.aliyuncs.com/avatar/default-avatar.png"
        )!!
    }

    // TODO 测试数据
    private val _blogList: List<BlogModel> = listOf(
        BlogModel(
            "1",
            "0",
            "admin",
            "",
            "这是标题",
            "这是描述",
            "这是内容",
//            LocalDateTime.of(2024, 3, 24, 14, 48, 22),
            "2024-03-24 14:48:22",
            0,
            0,
        ),
        BlogModel(
            "1",
            "0",
            "骚客",
            "",
            "这是标题",
            "这是描述",
            "这是内容",
//            LocalDateTime.of(2024, 3, 24, 14, 48, 22),
            "2024-03-24 14:48:22",
            0,
            0,
        ),
    )

    private val _tabs: List<String> = listOf("我发布的", "我赞过的")
    val tabs: List<String> = _tabs

    private val _myPostBlogList: List<BlogModel> = _blogList
    val myPostBlogList: List<BlogModel> = _myPostBlogList

    private val _myLikeBlogList: List<BlogModel> = _blogList
    val myLikeBlogList: List<BlogModel> = _myLikeBlogList
}