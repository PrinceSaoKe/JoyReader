package com.saoke.joyreader.ui.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.saoke.joyreader.SettingsActivity
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

        viewModel.username.observe(viewLifecycleOwner) {
            binding.username.text = it
        }

        binding.settingsButton.setOnClickListener {
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

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