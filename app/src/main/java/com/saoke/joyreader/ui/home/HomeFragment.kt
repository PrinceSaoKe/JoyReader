package com.saoke.joyreader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saoke.joyreader.databinding.FragmentHomeBinding
import com.saoke.joyreader.ui.blogpagelist.BlogListFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.getBlogList()

        viewModel.hotBlogList.observe(viewLifecycleOwner) {
            // 热门和最新的数据列表
            val listFragments: List<BlogListFragment> = listOf(
                BlogListFragment(viewModel.hotBlogList.value ?: listOf()),
                BlogListFragment(viewModel.latestBlogList.value ?: listOf()),
            )
            binding.blogPageList.setTabsAndPages(viewModel.tabs, listFragments)
        }
        viewModel.latestBlogList.observe(viewLifecycleOwner) {
            // 热门和最新的数据列表
            val listFragments: List<BlogListFragment> = listOf(
                BlogListFragment(viewModel.hotBlogList.value ?: listOf()),
                BlogListFragment(viewModel.latestBlogList.value ?: listOf()),
            )
            binding.blogPageList.setTabsAndPages(viewModel.tabs, listFragments)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}