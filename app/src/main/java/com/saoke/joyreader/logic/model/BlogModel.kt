package com.saoke.joyreader.logic.model

data class BlogModel(
    val articleId: String,
    val authorId: String,
    val authorName: String,
    val authorAvatarUrl: String,
    val title: String,
    val desc: String,
    val content: String,
    val createTime: String,
    val likes: Int,
    val clicks: Int,
)