package com.saoke.joyreader.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "这是发布文章页面"
    }
    val text: LiveData<String> = _text
}