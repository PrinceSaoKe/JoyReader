package com.saoke.joyreader.ui.blogpagelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.saoke.joyreader.databinding.BlogListBinding
import com.saoke.joyreader.logic.model.BlogModel

class BlogListFragment(private val blogList: List<BlogModel>) : Fragment() {
    private var _binding: BlogListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BlogListBinding.inflate(inflater, container, false)

        val homeListView = binding.homeListView
        homeListView.layoutManager = LinearLayoutManager(activity)
        homeListView.adapter = BlogListAdapter(blogList)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}