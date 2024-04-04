package com.saoke.joyreader.ui.blogpagelist

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.saoke.joyreader.databinding.BlogPageListBinding

class BlogPageListLayout(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {
    private val binding = BlogPageListBinding.inflate(LayoutInflater.from(context), this, true)

    fun setTabsAndPages(tabs: List<String>, pages: List<BlogListFragment>) {
        val activity = context as? FragmentActivity ?: return
        binding.pageView.adapter = BlogPageAdapter(activity, pages)

        TabLayoutMediator(binding.tabBar, binding.pageView) { tab, position ->
            tab.text = tabs[position]
        }.attach()
    }
}