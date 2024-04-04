package com.saoke.joyreader.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saoke.joyreader.databinding.FragmentMyBinding
import com.saoke.joyreader.ui.blogpagelist.BlogListFragment

class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        _binding = FragmentMyBinding.inflate(inflater, container, false)

        binding.username.text = viewModel.username
        binding.avatar.setImageResource(viewModel.avatar)

        // 我发布的 和 我收藏的 数据列表
        val listFragments: List<BlogListFragment> = listOf(
            BlogListFragment(viewModel.myPostBlogList),
            BlogListFragment(viewModel.myLikeBlogList),
        )

        binding.blogPageList.setTabsAndPages(viewModel.tabs, listFragments)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}