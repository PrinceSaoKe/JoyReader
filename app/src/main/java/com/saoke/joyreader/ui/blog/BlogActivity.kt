package com.saoke.joyreader.ui.blog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.saoke.joyreader.databinding.ActivityBlogBinding

class BlogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val blogId = intent.getStringExtra("model")
        val viewModel = ViewModelProvider(this)[BlogViewModel::class.java]
        viewModel.getBlog(blogId!!)

        viewModel.blogModel.observe(this) {
            title = it?.title
            binding.content.text = it?.content
        }

        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}