package com.saoke.joyreader.ui.my

import androidx.lifecycle.ViewModel
import com.saoke.joyreader.R
import com.saoke.joyreader.logic.model.BlogModel

class MyViewModel : ViewModel() {

    private val _username = "骚客."
    val username: String = _username

    private val _avatar = R.drawable.avatar
    val avatar: Int = _avatar

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