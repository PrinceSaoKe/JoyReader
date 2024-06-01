package com.saoke.joyreader.ui.blog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.saoke.joyreader.databinding.ActivityBlogBinding
import io.noties.markwon.Markwon
import io.noties.markwon.html.HtmlPlugin

class BlogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlogBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val markwon = Markwon.builder(this).usePlugin(HtmlPlugin.create()).build()

        val blogId = intent.getStringExtra("model")
        val viewModel = ViewModelProvider(this)[BlogViewModel::class.java]
        viewModel.getBlog(blogId!!)

        viewModel.blogModel.observe(this) {
            title = it?.title
            markwon.setMarkdown(binding.content, it?.content ?: "")
        }

        binding = ActivityBlogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}