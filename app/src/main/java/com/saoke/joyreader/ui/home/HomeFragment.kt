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

        // 热门和最新的数据列表
        val listFragments: List<BlogListFragment> = listOf(
            BlogListFragment(viewModel.hotBlogList),
            BlogListFragment(viewModel.latestBlogList),
        )

        binding.blogPageList.setTabsAndPages(viewModel.tabs, listFragments)

//        binding.button.setOnClickListener {
//            Retrofit.api.login(
//                "Prince",
//                "aaaaa"
//            ).enqueue(object : Callback<Model<LoginModel>> {
//                override fun onResponse(
//                    call: Call<Model<LoginModel>>,
//                    response: Response<Model<LoginModel>>
//                ) {
//                    if (response.isSuccessful) {
//                        val model = response.body()
//                        Log.i("MyLog", "token: ${model?.data?.token}")
//                        MMKV.defaultMMKV().encode("token", model?.data?.token)
//                        Log.i("MyLog", "${model?.base?.message}")
//                    } else {
//                        Log.i("MyLog", response.message())
//                    }
//                }
//
//                override fun onFailure(call: Call<Model<LoginModel>>, t: Throwable) {
//                    Log.i("MyLog", "请求失败: ${t.message}")
//                }
//            })
//        }
//
//        binding.button2.setOnClickListener {
//            Retrofit.api.getBlog("AR963580972194992128")
//                .enqueue(object : Callback<Model<BlogModel>> {
//                    override fun onResponse(
//                        call: Call<Model<BlogModel>>,
//                        response: Response<Model<BlogModel>>
//                    ) {
//                        if (response.isSuccessful) {
//                            val model = response.body()
//                            Log.i(
//                                "MyLog",
//                                "标题: ${model?.data?.title}, 简介: ${model?.data?.desc}"
//                            )
//                        } else {
//                            Log.i("MyLog", "${response.code()}")
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Model<BlogModel>>, t: Throwable) {
//                        Log.i("MyLog", "请求失败: ${t.message}")
//                    }
//                })
//        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}