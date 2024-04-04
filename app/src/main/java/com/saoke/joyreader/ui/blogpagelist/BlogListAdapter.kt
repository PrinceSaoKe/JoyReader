package com.saoke.joyreader.ui.blogpagelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saoke.joyreader.databinding.BlogListItemBinding
import com.saoke.joyreader.logic.extension.format
import com.saoke.joyreader.logic.model.BlogModel

class BlogListAdapter(private val blogList: List<BlogModel>) :
    RecyclerView.Adapter<BlogListAdapter.ViewHolder>() {

    /// RecyclerView中的每一项，binding的类型即单个项的布局
    inner class ViewHolder(private val binding: BlogListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /// 将数据模型中的数据绑定到视图上
        fun bindData(blog: BlogModel) {
            binding.blogListItemTitle.text = blog.title
            binding.blogListItemDesc.text = blog.desc
            binding.blogListItemAuthorName.text = blog.authorName
            binding.blogListItemClicks.text = "${blog.clicks}"
            binding.blogListItemLikes.text = "${blog.likes}"
            binding.blogListItemPostTime.text = blog.postTime.format()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BlogListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    /// 数据项滚动到屏幕内时执行
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(blogList[position])
    }
}