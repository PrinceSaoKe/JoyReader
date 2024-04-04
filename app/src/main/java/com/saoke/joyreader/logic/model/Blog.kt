package com.saoke.joyreader.logic.model

import java.time.LocalDateTime

data class BlogModel(
    val blogId: String,
    val authorId: String,
    val authorName: String,
    val authorAvatarUrl: String,
    val title: String,
    val desc: String,
    val content: String,
    val postTime: LocalDateTime,
    val likes: Int,
    val clicks: Int,
)
