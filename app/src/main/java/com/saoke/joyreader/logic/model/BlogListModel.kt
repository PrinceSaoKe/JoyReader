package com.saoke.joyreader.logic.model

data class BlogListModel(
    val articles: List<BlogModel>,
    val totalPages: Int,
)